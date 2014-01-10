package by.premia.olga.project.controller;

import by.premia.olga.project.util.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Vlad Abramov
 */
@Controller
@RequestMapping(value = "/image", method = RequestMethod.GET)
public class ImageController {

    @RequestMapping("{from}/{content}")
    public void getImage(@PathVariable String from, @PathVariable int content, HttpServletResponse res) {
        Utils.sendImage(res, from, content);
    }

    @RequestMapping("upload/{from}/{content}")
    public void uploadImage(@PathVariable String from, @PathVariable int content, HttpServletResponse res) {
//        Utils.sendImage(res, from, content);
    }
}
