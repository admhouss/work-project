package by.premiya.olga.project.service.impl;

import by.premiya.olga.project.dao.UserDao;
import by.premiya.olga.project.entity.User;
import by.premiya.olga.project.service.UserService;
import by.premiya.olga.project.util.EditUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public EditUser updateUser(EditUser editUser) {
        User user = userDao.getUserByLogin(editUser.getLogin());
        if (!editUser.getLogin().equals(editUser.getNewLogin())) {
            if (userDao.getUserByLogin(editUser.getNewLogin()) == null) {
                user.setLogin(editUser.getNewLogin());
                editUser.setLoginIsFree(true);
            } else {
                editUser.setLoginIsFree(false);
                try {
                    return editUser.clone();
                } catch (CloneNotSupportedException ignored) {
                }
            }
        }
        user.setLastName(editUser.getNewLastName());
        user.setFirstName(editUser.getNewFirstName());
        if (!editUser.getNewPassword().equals("")) {
            user.setPasswordHash(passwordEncoder.encodePassword(editUser.getNewPassword(), user.getLogin()));
        }
        userDao.updateUser(user);
        editUser.setSuccess(true);
        try {
            return editUser.clone();
        } catch (CloneNotSupportedException ignored) {
        }
        return null;
    }

}
