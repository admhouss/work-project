package by.premiya.olga.project.service.impl;

import by.premiya.olga.project.dao.ProductionDao;
import by.premiya.olga.project.entity.Wheel;
import by.premiya.olga.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Vlad Abramov
 */
@Service
public class ProductServiceImpl implements ProductService {

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
}
