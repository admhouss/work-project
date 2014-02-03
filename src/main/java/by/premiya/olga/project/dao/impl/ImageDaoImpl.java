package by.premiya.olga.project.dao.impl;

import by.premiya.olga.project.dao.ImageDao;
import by.premiya.olga.project.entity.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author vlad
 */
public class ImageDaoImpl implements ImageDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Image image) {
        getSession().saveOrUpdate(image);
    }

    @Override
    public Image getById(Integer id) {
        return (Image) getSession().get(Image.class, id);
    }

    @Override
    public void updateImage(Image image) {
        getSession().saveOrUpdate(image);
    }

    @Override
    public void removeImage(Image image) {
        getSession().delete(image);
    }
}
