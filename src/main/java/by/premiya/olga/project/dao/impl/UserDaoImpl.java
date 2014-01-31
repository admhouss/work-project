package by.premiya.olga.project.dao.impl;

import by.premiya.olga.project.dao.UserDao;
import by.premiya.olga.project.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author vabramov
 */
@SuppressWarnings("unchecked")
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Insert new {@code User} to DB. There is no checking for ensures that {@code newUser} is exactly stored in DB.
     *
     * @param newUser user that you want ot store in DB.
     */
    @Override
    @Transactional
    public void addUser(User newUser) {
        getSession().save(newUser);
    }

    @Override
    @Transactional
    public void updateUser(User updatedUser) {
        getSession().saveOrUpdate(updatedUser);
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

    /**
     * Removes {@code User} from DB by identifier.
     * @param id user identifier.
     * */
    @Override
    @Transactional
    public void removeUserById(Integer id) {
        User user = getUserById(id);

        if (user != null) {
            getSession().delete(user);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        Query query = getSession().createQuery("from User");
        return (List<User>) query.list();
    }

    @Override
    @Transactional
    public void removeUserByLogin(String login) {
        User user = getUserByLogin(login);

        if (user != null) {
            getSession().delete(user);
        }
    }

    @Override
    @Transactional
    public void removeUser(User user) {
        if (user != null) {
            getSession().delete(user);
        }
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
