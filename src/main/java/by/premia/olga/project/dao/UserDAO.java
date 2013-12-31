package by.premia.olga.project.dao;

import by.premia.olga.project.entity.User;

import java.util.List;

/**
 * @author vabramov
 */
public interface UserDAO {

    void addUser(User newUser);

    void removeUserById(Integer id);

    void updateUser(User updatedUser);

    User getUserById(Integer id);

    User getUserByEmail(String email);

    List<User> getUsers();
}


