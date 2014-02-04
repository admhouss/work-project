package by.premiya.olga.project.dao.impl;

import by.premiya.olga.project.dao.ProductDao;
import by.premiya.olga.project.entity.Accumulator;
import by.premiya.olga.project.entity.Wheel;
import by.premiya.olga.project.entity.constants.comparators.wheels.WheelsCompare;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

import java.util.*;

/**
 * @author Vlad Abramov
 */
@SuppressWarnings("unchecked")
public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Object product) {
        getSession().save(product);
    }

    @Override
    public void delete(Object product) {
        getSession().delete(product);
    }

    @Override
    public void update(Object product) {
        getSession().merge(product);
    }

    @Override
    public Wheel getWheelById(Integer id) {
        return (Wheel) getSession().get(Wheel.class, id);
    }

    @Override
    public Accumulator getAccumulatorById(Integer id) {
        return (Accumulator) getSession().get(Accumulator.class, id);
    }

    @Override
    public Object getProductByModel(String productName, String model) {
        Query query = null;
        switch (productName) {
            case "wheel":
            case "wheels":
                query = getSession().createQuery("from Wheel where model=:model");
                query.setParameter("model", model);
                break;
        }
        return query != null ? query.uniqueResult() : null;
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
                        .add(Projections.property("producer"), "producer")
                        .add(Projections.property("model"), "model"))
                .setResultTransformer(Transformers.aliasToBean(Wheel.class));
        for (Object wheel : cr.list()) {
            Wheel cur = (Wheel)wheel;
            models.add(cur.getProducer().getString() + " " + cur.getModel());
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
