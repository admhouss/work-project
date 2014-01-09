package by.premia.olga.project.controller;

import by.premia.olga.project.entity.User;
import by.premia.olga.project.util.Pages;
import by.premia.olga.project.util.annotations.ActiveUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author vabramov
 */
@Controller
@RequestMapping("/admin/editor")
public class AdminController {


    @RequestMapping(method = RequestMethod.GET)
    private String signUp() {


        return Pages.HOME_PAGE;
    }

    @RequestMapping(value = "/new/wheel",method = RequestMethod.GET)
    private String newWheel(@ActiveUser User user) {
        return "";
    }
}
