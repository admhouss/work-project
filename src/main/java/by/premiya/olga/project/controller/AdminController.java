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

/**
 * @author vabramov
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    private String defaultAdmin() {
        return Pages.REDIRECT + Pages.ADMIN_EDITOR;
    }

    @RequestMapping(value = "editor", method = RequestMethod.GET)
    private String adminEditor(@ActiveUser User user, ModelMap model) {
        model.put("userId", user.getId());
        model.put("userFullName", user.getFullName());
        return Pages.HOME_PAGE;
    }

    @RequestMapping(value = "new/wheel", method = RequestMethod.GET)
    private String newWheel(@ActiveUser User user) {
        return "";
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    private String users(ModelMap model) {
        userService.getAllUsers();
        return Pages.USERS_PAGE;
    }

    @RequestMapping(value = "/user/edit/{userLogin}", method = RequestMethod.GET)
    private String editUserPage(ModelMap model, @PathVariable String userLogin, @ActiveUser(withRole = UserRole.ROLE_SUPERVISOR) User curUser) {
        if (userLogin.equals(curUser.getLogin())) {
            model.put("user", curUser);
        } else {
            model.put("user", userService.getByLogin(userLogin));
        }
        return Pages.PROFILE_PAGE;
    }
}
