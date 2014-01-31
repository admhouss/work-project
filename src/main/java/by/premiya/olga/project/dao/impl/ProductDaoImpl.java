package by.premiya.olga.project.dao.impl;

import by.premiya.olga.project.dao.ProductDao;
import by.premiya.olga.project.entity.Wheel;
import by.premiya.olga.project.entity.constants.comparators.wheels.WheelsCompare;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Vlad Abramov
 */
//@Repository
@SuppressWarnings("unchecked")
public class ProductDaoImpl implements ProductDao {

    //    @Inject
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Object product) {
        getSession().save(product);
    }

    @Override
    public List getProducts(String name) {
        List products = new LinkedList<>();
        switch (name) {
            case "wheels":
                products = getWheels(null);
                break;
        }
        return products;
    }

    @Override
    public List<Wheel> getWheels(Map<String, String> searchParams) {
        List<Wheel> wheels = new LinkedList<>();
        if (searchParams == null) {
            Query query = getSession().createQuery("from Wheel");
            wheels = (List<Wheel>) query.list();
            Collections.sort(wheels, WheelsCompare.BY_NAME);
        } else {

        }
        return wheels;
    }

    @Override
    public List<String> getWheelModels() {
        List<String> models = new LinkedList<>();
        Criteria cr = getSession().createCriteria(Wheel.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.property("model"), "model"))
                .setResultTransformer(Transformers.aliasToBean(Wheel.class));
        for (Object wheel : cr.list()) {
            models.add(((Wheel)wheel).getModel());
        }
        return models;
    }

    @Override
    public List<String> getAccumulatorModels() {
        return null;
    }

    @Override
    public List<String> getRadiatorModels() {
        return null;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
