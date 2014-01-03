package by.premia.olga.project.controller;

import by.premia.olga.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
//        if (true) throw new RuntimeException();
		model.addAttribute("message", "Hello world!");
        userService.getByLogin("111");
		return "hello";
	}
}