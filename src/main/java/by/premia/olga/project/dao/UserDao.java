package by.premia.olga.project.dao;

import by.premia.olga.project.entity.User;

import java.util.List;

/**
 * @author vabramov
 */
public interface UserDao {

    void addUser(User newUser);

    void removeUserById(Integer id);

    void updateUser(User updatedUser);

    User getUserById(Integer id);

    User getUserByLogin(String login);

    List<User> getUsers();
}


