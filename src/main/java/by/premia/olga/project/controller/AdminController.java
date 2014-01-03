package by.premia.olga.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author vabramov
 */
@Controller
@RequestMapping("/admin")
public class AdminController {


    @RequestMapping(method = RequestMethod.GET)
    private String signUp() {


        return "hello";
    }
}
