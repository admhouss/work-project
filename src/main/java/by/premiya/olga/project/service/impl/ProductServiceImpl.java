package by.premiya.olga.project.service.impl;

import by.premiya.olga.project.dao.ProductionDao;
import by.premiya.olga.project.entity.Wheel;
import by.premiya.olga.project.service.ProductService;
import by.premiya.olga.project.util.json.NewItemJSON;
import by.premiya.olga.project.util.json.PairJSON;
import by.premiya.olga.project.util.json.PropertiesJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author Vlad Abramov
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductionDao productionDao;

    @Override
    @Transactional(readOnly = true)
    public List getProducts(String name) {
        return productionDao.getProducts(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Wheel> getWheels(Map<String, String> searchParams) {
        return productionDao.getWheels(searchParams);
    }

    @Override
    public void addNewProduct(String productName, NewItemJSON properties) {
        switch (productName) {
            case "wheels": getInsertObject(new Wheel(), properties); break;
        }
    }

    private void getInsertObject(Object insert, NewItemJSON properties) {
        if (logger.isDebugEnabled()) {
            logger.debug("Start parse JSON for new product");
        }
        Class clazz = insert.getClass();
        try {
            for (PairJSON<String, PairJSON<String,String>> pair : properties.getProperties()) {
                if (pair.getFirst().equals("label")) {
                    Field field = clazz.getDeclaredField(pair.getSecond().getFirst());
                    field.ge
                }
                clazz.getDeclaredField(pair.getSecond().getFirst()).set(insert,pair.);
                clazz.getDeclaredField(pair.getSecond().getFirst()).set(insert,pair.);
            }
        } catch (NoSuchFieldException e) {
            logger.error("No such field in product class; " + e.getMessage());
        }
    }


}
