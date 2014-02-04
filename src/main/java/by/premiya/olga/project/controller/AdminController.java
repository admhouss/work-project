package by.premiya.olga.project.controller;

import by.premiya.olga.project.util.Pages;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author vabramov
 */
@Controller
public class AdminController {

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_SUPERVISOR')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return Pages.REDIRECT + Pages.ADMIN_EDITOR;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_SUPERVISOR')")
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String login() {
        return Pages.REDIRECT + Pages.ADMIN_EDITOR;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_SUPERVISOR')")
    @RequestMapping(value = "/auth/administration", method = RequestMethod.GET)
    private String defaultAdmin() {
        return Pages.REDIRECT + Pages.ADMIN_EDITOR;
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_SUPERVISOR')")
    @RequestMapping(value = "/auth/administration/editor",method = RequestMethod.GET)
    private String adminEditor() {
        return Pages.ADMIN_HOME_PAGE;
    }

}
