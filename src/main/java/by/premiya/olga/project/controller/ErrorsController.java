package by.premiya.olga.project.controller;

import by.premiya.olga.project.util.Pages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author vabramov
 */
@Controller
public class ErrorsController {

    @RequestMapping("/error")
    private String errorHandle(ModelMap model, @RequestParam int code) {
        model.put("error", code);
        return Pages.ERROR_PAGE;
    }
}
