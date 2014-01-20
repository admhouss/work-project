package by.premiya.olga.project.dao.impl;

import by.premiya.olga.project.dao.ProductionDao;
import by.premiya.olga.project.entity.User;
import by.premiya.olga.project.entity.Wheel;
import by.premiya.olga.project.entity.constants.comparators.wheels.WheelsCompare;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Vlad Abramov
 */
@SuppressWarnings("unchecked")
public class ProductionDaoImpl implements ProductionDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List getProducts(String name) {
        List products = new LinkedList<>();
        switch (name) {
            case "wheels": products = getWheels(null); break;
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

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
