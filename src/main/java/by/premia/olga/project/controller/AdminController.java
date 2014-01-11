package by.premia.olga.project.controller;

import by.premia.olga.project.entity.User;
import by.premia.olga.project.util.Pages;
import by.premia.olga.project.util.annotations.ActiveUser;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author vabramov
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

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

    @RequestMapping(value = "new/wheel",method = RequestMethod.GET)
    private String newWheel(@ActiveUser User user) {
        return "";
    }

    @Secured("ROLE_ADMINISTRATOR")
    @RequestMapping(value = "users", method = RequestMethod.GET)
    private String users() {
        return Pages.HOME_PAGE;
    }
}
