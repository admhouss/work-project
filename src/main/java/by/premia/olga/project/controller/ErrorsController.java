package by.premia.olga.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author vabramov
 */
@Controller
@RequestMapping("/error")
public class ErrorsController {

    @RequestMapping("/401")
    private String error401(Exception e) {
        return "error";
    }
}
