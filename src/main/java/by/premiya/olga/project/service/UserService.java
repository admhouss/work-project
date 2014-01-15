package by.premiya.olga.project.service;

import by.premiya.olga.project.entity.User;
import by.premiya.olga.project.util.EditUser;

import java.util.List;

/**
 * @author vabramov
 */
public interface UserService {
    User getById(int id);

    User getByLogin(String login);

    List<User> getAllUsers();

    void deleteByLogin(String login);

    void delete(User user);

    EditUser updateUser(EditUser editUser);
}
