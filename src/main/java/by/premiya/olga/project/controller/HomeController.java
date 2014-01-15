package by.premiya.olga.project.controller;

import by.premiya.olga.project.service.UserService;
import by.premiya.olga.project.util.Pages;
import by.premiya.olga.project.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String indexPage(ModelMap model, HttpServletResponse response, HttpServletRequest request) {
        Utils.setLogoutCookie(response);
        return Pages.HOME_PAGE;
	}

    @RequestMapping(value = "search/light", method = {RequestMethod.GET, RequestMethod.POST})
    public String lightSearch(ModelMap model, @RequestParam String text) {
        return Pages.HOME_PAGE;
    }

    @RequestMapping(value = "auth", method = RequestMethod.GET)
    public String login() {
        return Pages.REDIRECT + Pages.ADMIN_EDITOR;
    }
}