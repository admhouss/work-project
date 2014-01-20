package by.premiya.olga.project.controller;

import by.premiya.olga.project.util.Pages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage() {
//        Utils.setLogoutCookie(response);
        return Pages.HOME_PAGE;
    }

    @RequestMapping(value = "/search/light", method = {RequestMethod.GET, RequestMethod.POST})
    public String lightSearch(ModelMap model, @RequestParam String text) {
        return Pages.HOME_PAGE;
    }

}