package by.premiya.olga.project.controller;

import by.premiya.olga.project.entity.User;
import by.premiya.olga.project.service.UserService;
import by.premiya.olga.project.util.json.EditUser;
import by.premiya.olga.project.util.Pages;
import by.premiya.olga.project.util.annotations.ActiveUser;
import by.premiya.olga.project.util.auth.UserRole;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author vabramov
 */
@Controller
@PreAuthorize(value = "isAuthenticated()")
@RequestMapping("/auth/administration")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    private String defaultAdmin() {
        return Pages.REDIRECT + Pages.ADMIN_EDITOR;
    }

    @Secured(value = "ROLE_SUPERVISOR")
    @RequestMapping(value = "users", method = RequestMethod.GET)
    private String users(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return Pages.USERS_PAGE;
    }

    @Secured(value = "ROLE_SUPERVISOR")
    @RequestMapping(value = "users/edit/{userLogin}", method = RequestMethod.GET)
    private String editUserPage(ModelMap model, @PathVariable String userLogin, @ActiveUser(UserRole.ROLE_SUPERVISOR) User curUser) {
        if (userLogin.equals(curUser.getLogin())) {
            model.put("user", curUser);
        } else {
            model.put("user", userService.getByLogin(userLogin));
        }
        return Pages.PROFILE_PAGE;
    }

    @Secured(value = "ROLE_SUPERVISOR")
    @RequestMapping(value = "users/do/edit/", method = RequestMethod.POST, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody EditUser editActionUserPage(@RequestBody EditUser editUser) {
        return userService.updateUser(editUser);
    }

    @Secured(value = "ROLE_SUPERVISOR")
    @RequestMapping(value = "users/edit/remove", method = RequestMethod.POST)
    private @ResponseBody boolean removeActionUserPage(@RequestParam String login) {
        userService.deleteByLogin(login);
        return true;
    }

    @Secured(value = "ROLE_SUPERVISOR")
    @RequestMapping(value = "users/edit/repo/{userLogin}", method = RequestMethod.POST)
    private @ResponseBody String editRepo(@PathVariable String userLogin, @ActiveUser(UserRole.ROLE_SUPERVISOR) User curUser) {
        return userService.changeRole(userLogin);
    }

    @Secured(value = "ROLE_SUPERVISOR")
    @RequestMapping(value = "users/edit/get/{userLogin}", method = RequestMethod.POST)
    private @ResponseBody EditUser getEditUser(@PathVariable String userLogin) {
        return userService.getEditUser(userLogin);
    }

}
