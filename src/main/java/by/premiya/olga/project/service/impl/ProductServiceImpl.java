package by.premiya.olga.project.service.impl;

import by.premiya.olga.project.dao.ImageDao;
import by.premiya.olga.project.dao.ProductDao;
import by.premiya.olga.project.entity.Accumulator;
import by.premiya.olga.project.entity.Image;
import by.premiya.olga.project.entity.Wheel;
import by.premiya.olga.project.entity.constants.BasicConstant;
import by.premiya.olga.project.service.ProductService;
import by.premiya.olga.project.util.json.ItemJSON;
import by.premiya.olga.project.util.json.PairJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author Vlad Abramov
 */
@Service
@SuppressWarnings("unchecked")
public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ImageDao imageDao;

    @Override
    @Transactional(readOnly = true)
    public List getProducts(String name) {
        return productDao.getProducts(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Object getProductByModel(String productName, String model) {
        return productDao.getProductByModel(productName, model);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Wheel> getWheels(Map<String, String> searchParams) {
        return productDao.getWheels(searchParams);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addNewProduct(String productName, ItemJSON properties) {
        Object insert = null;
        switch (productName) {
            case "wheels":
                insert = getInsertObject(Wheel.class, properties);
                properties.setObjectProduct(productName);
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

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateProduct(Object product) {
        productDao.update(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemJSON getEditItem(Integer id) {
        Object product = getById(id);
        return getItemJSONFromProduct(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Object getById(Integer id) {
        Wheel wheel = productDao.getWheelById(id);
        Accumulator accumulator;
        if (wheel == null) {
            accumulator = productDao.getAccumulatorById(id);
            if (accumulator == null) {

            } else {
                return accumulator;
            }
        } else {
            return wheel;
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean removeItem(Integer id) {
        Object product = getById(id);
        try {
            Field imageId = product.getClass().getDeclaredField("imageId");
            imageId.setAccessible(true);
            Image image = imageDao.getById((Integer)imageId.get(product));
            if (image != null) {
                File file = new File(image.getPath());
                file.delete();
                imageDao.removeImage(image);
            }
            productDao.delete(product);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
            return false;
        }
        return true;
    }

    private Object getInsertObject(Class clazz, ItemJSON properties) {
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
                            if (pair.getSecond().getFirst().equals("model")) {
                                properties.setObjectModel(pair.getSecond().getSecond());
                            } else if (pair.getSecond().getFirst().equals("producer")) {
                                properties.setObjectProducer(pair.getSecond().getSecond());
                            }
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

    private void setField(Class clazz, Object insert, PairJSON<String, PairJSON<String, String>> pair, Field field)
            throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            NumberFormatException {

        field.setAccessible(true);
        String methodName = getMethodName(pair.getSecond().getFirst());
        Method method = clazz.getMethod("set" + methodName, field.getType());
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
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private Enum getEnumValue(Class clazz, String value) {
        return Enum.valueOf(clazz, value);
    }


    private ItemJSON getItemJSONFromProduct(Object product) {
        Class clazz = product.getClass();
        ItemJSON itemJSON = new ItemJSON();
        for (Field field : clazz.getDeclaredFields()) {
            try {
                if (!field.getName().equals("serialVersionUID") && !field.getName().equals("imageId")) {
                    field.setAccessible(true);
                    String value = field.get(product).toString();
                    if (isBasicConstant(field)){
                        itemJSON.addProperty(new PairJSON<>("enum", new PairJSON<>(field.getName(), value)));
                    } else {
                        itemJSON.addProperty(new PairJSON<>("label", new PairJSON<>(field.getName(), value)));
                    }
                    if (field.getName().equals("producer")) {
                        itemJSON.setObjectProducer(value);
                    } else if (field.getName().equals("model")) {
                        itemJSON.setObjectModel(value);
                    }
                }
            } catch (IllegalAccessException ignored) {
            }
        }
        return itemJSON;
    }

    private boolean isBasicConstant(Field field) {
        Class[] interfaces = field.getType().getInterfaces();
        for (Class cur : interfaces) {
            if (cur.equals(BasicConstant.class)) return true;
        }
        return false;
    }
}
