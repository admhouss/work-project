package by.premiya.olga.project.controller;

import by.premiya.olga.project.entity.User;
import by.premiya.olga.project.service.UserService;
import by.premiya.olga.project.util.Pages;
import by.premiya.olga.project.util.annotations.ActiveUser;
import by.premiya.olga.project.util.auth.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author vabramov
 */
@Controller
@RequestMapping("/auth/administration")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    private String defaultAdmin() {
        return Pages.REDIRECT + Pages.ADMIN_EDITOR;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    private String users(ModelMap model) {
        model.addAttribute(userService.getAllUsers());
        return Pages.USERS_PAGE;
    }

    @RequestMapping(value = "/users/edit/{userLogin}", method = RequestMethod.GET)
    private String editUserPage(ModelMap model, @PathVariable String userLogin, @ActiveUser(UserRole.ROLE_SUPERVISOR) User curUser) {
        if (userLogin.equals(curUser.getLogin())) {
            model.put("user", curUser);
        } else {
            model.put("user", userService.getByLogin(userLogin));
        }
        return Pages.PROFILE_PAGE;
    }
    @RequestMapping(value = "/users/do/edit/", method = RequestMethod.GET)
    private String editActionUserPage(ModelMap model, @PathVariable String userLogin, @ActiveUser(UserRole.ROLE_SUPERVISOR) User curUser) {
        if (userLogin.equals(curUser.getLogin())) {
            model.put("user", curUser);
        } else {
            model.put("user", userService.getByLogin(userLogin));
        }
        return Pages.PROFILE_PAGE;
    }
}
