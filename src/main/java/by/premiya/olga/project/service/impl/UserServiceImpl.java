package by.premiya.olga.project.service.impl;

import by.premiya.olga.project.dao.UserDao;
import by.premiya.olga.project.entity.User;
import by.premiya.olga.project.service.UserService;
import by.premiya.olga.project.util.auth.UserRole;
import by.premiya.olga.project.util.json.EditUser;
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
                if (user == null) {
                    user = new User();
                }
                user.setLogin(editUser.getNewLogin());
                editUser.setLoginIsFree(true);
            } else {
                editUser.setLoginIsFree(false);
                return editUser;
            }
        }
        user.setLastName(editUser.getNewLastName());
        user.setFirstName(editUser.getNewFirstName());
        editUser.setUserRole(user.getRole());
        if (!editUser.getNewPassword().equals("")) {
            user.setPasswordHash(passwordEncoder.encodePassword(editUser.getNewPassword(), user.getLogin()));
        }
        userDao.updateUser(user);
        editUser.setSuccess(true);
        return editUser;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String changeRole(String login) {
        User user = userDao.getUserByLogin(login);
        String result = "star";
        if (user.getRole().equals(UserRole.ROLE_ADMINISTRATOR)) {
            user.setRole(UserRole.ROLE_SUPERVISOR);
        } else {
            user.setRole(UserRole.ROLE_ADMINISTRATOR);
            result = "un-" + result;
        }
        userDao.updateUser(user);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public EditUser getEditUser(String userLogin) {
        User user = userDao.getUserByLogin(userLogin);
        return new EditUser(user);
    }
}
