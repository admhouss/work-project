package by.premiya.olga.project.dao;

import by.premiya.olga.project.entity.User;

import java.util.List;

/**
 * Interface for getting information about users from data base
 * @author vabramov
 */
public interface UserDao {

    /**
     * Insert new {@code User} to DB. There is no checking for ensures that {@code newUser} is exactly stored in DB.
     * @param newUser user that you want ot store in DB.
     * */
    void addUser(User newUser);

    /**
     * Removes {@code User} from DB by identifier.
     * @param id user identifier.
     * */
    void removeUserById(Integer id);

    void updateUser(User updatedUser);

    User getUserById(Integer id);

    User getUserByLogin(String login);

    List<User> getUsers();

    void removeUserByLogin(String login);

    void removeUser(User user);
}


