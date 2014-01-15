package by.premiya.olga.project.controller;

import by.premiya.olga.project.entity.User;
import by.premiya.olga.project.util.Pages;
import by.premiya.olga.project.util.Utils;
import by.premiya.olga.project.util.annotations.ActiveUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author vlad
 */
@Controller
@RequestMapping("/auth/administration/editor")
public class AdminEditorController {
    @RequestMapping(method = RequestMethod.GET)
    private String adminEditor(HttpServletRequest request, HttpServletResponse response) {
        return Pages.ADMIN_HOME_PAGE;
    }

    @RequestMapping(value = "new/wheel", method = RequestMethod.GET)
    private String newWheel(@ActiveUser User user) {
        return "";
    }
}
