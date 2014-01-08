package by.premia.olga.project.controller;

import by.premia.olga.project.service.UserService;
import by.premia.olga.project.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String indexPage(ModelMap model) {
//        if (true) throw new RuntimeException();
		model.addAttribute("message", "Hello world!");
        userService.getByLogin("111");
		return Pages.HOME_PAGE;
	}

    @RequestMapping(value = "search/light", method = {RequestMethod.GET, RequestMethod.POST})
    public String lightSearch(ModelMap model, @RequestParam String text) {
        return Pages.HOME_PAGE;
    }
}