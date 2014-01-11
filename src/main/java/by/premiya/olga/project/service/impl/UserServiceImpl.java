package by.premiya.olga.project.service.impl;

import by.premiya.olga.project.dao.UserDao;
import by.premiya.olga.project.entity.User;
import by.premiya.olga.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author vabramov
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByLogin(String login) {

        return userDao.getUserByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getUsers();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByLogin(String login) {
        userDao.removeUserByLogin(login);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(User user) {
        userDao.removeUser(user);
    }

}
