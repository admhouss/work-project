package by.premia.olga.project.dao.impl;

import by.premia.olga.project.dao.UserDAO;
import by.premia.olga.project.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author vabramov
 */
@Repository
public class UserDaoImpl implements UserDAO {

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    @Qualifier(value = "entityManagerFactory")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void addUser(User newUser) {
        entityManager.persist(newUser);
    }

    @Override
    @Transactional
    public void updateUser(User updatedUser) {
        entityManager.refresh(updatedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        Query query = entityManager.createQuery("from User user where user.login = :email");
        query.setParameter("email", email);

        @SuppressWarnings("rawtypes")
        List resultList = query.getResultList();

        return (resultList.isEmpty()) ? null : (User) resultList.get(0);
    }

    @Override
    @Transactional
    public void removeUserById(Integer id) {
        User user = getUserById(id);

        if (user != null) {
            entityManager.remove(user);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        Query query = entityManager.createQuery("from User");
        return (List<User>) query.getResultList();
    }
}
