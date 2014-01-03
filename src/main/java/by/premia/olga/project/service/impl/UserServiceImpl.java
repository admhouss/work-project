package by.premia.olga.project.service.impl;

import by.premia.olga.project.dao.UserDao;
import by.premia.olga.project.entity.User;
import by.premia.olga.project.service.UserService;
import by.premia.olga.project.util.auth.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author vabramov
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public User getByLogin(String login) {

        return userDao.getUserByLogin(login);
    }

}
