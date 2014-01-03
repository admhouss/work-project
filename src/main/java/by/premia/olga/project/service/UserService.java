package by.premia.olga.project.service;

import by.premia.olga.project.entity.User;

/**
 * @author vabramov
 */
public interface UserService {
    User getByLogin(String login);
}
