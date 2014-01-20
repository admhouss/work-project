package by.premiya.olga.project.controller;

import by.premiya.olga.project.util.Pages;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author vlad
 */
@Controller
@RequestMapping("/auth/administration/editor")
public class AdminEditorController {

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET)
    private String adminEditor() {
        return Pages.ADMIN_HOME_PAGE;
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "new/wheel", method = RequestMethod.GET)
    private String newWheel() {
        return Pages.NEW_WHEEL_PAGE;
    }
}
