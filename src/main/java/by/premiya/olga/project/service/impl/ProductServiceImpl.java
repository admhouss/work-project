package by.premiya.olga.project.service.impl;

import by.premiya.olga.project.dao.ProductDao;
import by.premiya.olga.project.entity.Wheel;
import by.premiya.olga.project.service.ProductService;
import by.premiya.olga.project.util.json.NewItemJSON;
import by.premiya.olga.project.util.json.PairJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author Vlad Abramov
 */
@Service
public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductDao productDao;

    @Override
    @Transactional(readOnly = true)
    public List getProducts(String name) {
        return productDao.getProducts(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Wheel> getWheels(Map<String, String> searchParams) {
        return productDao.getWheels(searchParams);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addNewProduct(String productName, NewItemJSON properties) {
        Object insert = null;
        switch (productName) {
            case "wheels":
                insert = getInsertObject(Wheel.class, properties);
                break;
        }
        if (insert != null) {
            productDao.save(insert);
            properties.setSuccess(true);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllModels() {
        List<String> models = productDao.getWheelModels();
        return models;
    }

    private Object getInsertObject(Class clazz, NewItemJSON properties) {
        if (logger.isTraceEnabled()) {
            logger.trace("Start parse JSON for new product");
        }
        Object insert = null;
        try {
            insert = clazz.newInstance();
            for (PairJSON<String, PairJSON<String, String>> pair : properties.getProperties()) {
                Field field = clazz.getDeclaredField(pair.getSecond().getFirst());
                if ("".equals(pair.getSecond().getSecond()) || "NAN".equals(pair.getSecond().getSecond())) {
                    if (!field.getAnnotation(Column.class).nullable()) {
                        properties.addNotSetField(pair.getSecond().getFirst());
                    }
                } else {
                    if (pair.getSecond().getSecond() != null) {
                        try {
                            setField(clazz, insert, pair, field);
                        } catch (IllegalAccessException ignored) {
                        } catch (NoSuchMethodException | InvocationTargetException | NumberFormatException e) {
                            properties.addFailedField(pair.getSecond().getFirst());
                        }
                    }
                }
            }
            if (!properties.getFailedFields().isEmpty() || !properties.getNotSetFields().isEmpty()) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Found empty fields or some fails in setting fields");
                }
                return null;
            }
        } catch (InstantiationException | IllegalAccessException ignored) {
        } catch (NoSuchFieldException e) {
            logger.error("No such field in product class; " + e.getMessage());
        }
        return insert;
    }

    @SuppressWarnings("unchecked")
    private void setField(Class clazz, Object insert, PairJSON<String, PairJSON<String, String>> pair, Field field)
            throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            NumberFormatException {

        field.setAccessible(true);
        String methodName = getMethodName(pair.getSecond().getFirst());
        Method method = clazz.getMethod("set"+methodName, field.getType());
        Object insertParam = null;
        if (pair.getFirst().equals("label")) {
            if (field.getType().equals(Integer.class)) {
                insertParam = Integer.parseInt(pair.getSecond().getSecond());
            } else if (field.getType().equals(Float.class)) {
                insertParam = Float.parseFloat(pair.getSecond().getSecond());
            } else if (field.getType().equals(String.class)) {
                insertParam = pair.getSecond().getSecond();
            }
        } else {
            insertParam = getEnumValue(field.getType(), pair.getSecond().getSecond());
        }
        method.invoke(insert, insertParam);
    }

    private String getMethodName(String name) {
        return name.substring(0,1).toUpperCase() + name.substring(1);
    }

    private Enum getEnumValue(Class clazz, String value) {
        return Enum.valueOf(clazz, value);
    }
}
