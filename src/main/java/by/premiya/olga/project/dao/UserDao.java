package by.premiya.olga.project.dao;

import by.premiya.olga.project.entity.User;

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

    void removeUserByLogin(String login);

    void removeUser(User user);
}


