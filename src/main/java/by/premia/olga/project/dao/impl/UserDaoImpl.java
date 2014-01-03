package by.premia.olga.project.dao.impl;

import by.premia.olga.project.dao.UserDAO;
import by.premia.olga.project.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author vabramov
 */
public class UserDaoImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addUser(User newUser) {
        getSession().persist(newUser);
    }

    @Override
    @Transactional
    public void updateUser(User updatedUser) {
        getSession().refresh(updatedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Integer id) {
        return (User) getSession().get(User.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByLogin(String login) {
        Query query = getSession().createQuery("from User user where user.login = :login");
        query.setParameter("login", login);

        @SuppressWarnings("rawtypes")
        List resultList = query.list();

        return (resultList.isEmpty()) ? null : (User) resultList.get(0);
    }

    @Override
    @Transactional
    public void removeUserById(Integer id) {
        User user = getUserById(id);

        if (user != null) {
            getSession().delete(user);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        Query query = getSession().createQuery("from User");
        return (List<User>) query.list();
    }

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}
