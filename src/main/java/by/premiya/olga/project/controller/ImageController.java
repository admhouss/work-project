package by.premiya.olga.project.controller;

import by.premiya.olga.project.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vlad Abramov
 */
@Controller
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    private Utils utils;

    @RequestMapping(value = "get/{imageId}", method = RequestMethod.GET)
    public void getImage(@PathVariable Integer imageId, HttpServletResponse res) {
        utils.sendImage(res, imageId);
    }

    @RequestMapping(value = "upload/{productName}/{producer}/{model}", method = RequestMethod.POST)
    public @ResponseBody Boolean uploadImage(HttpServletRequest req
            , @PathVariable String productName
            , @PathVariable String producer
            , @PathVariable String model) {
        return utils.uploadImage(req, productName, producer, model);
    }

    @RequestMapping(value = "gif/{from}/{content}", method = RequestMethod.GET)
    public void utilImage(@PathVariable String from, @PathVariable String content, HttpServletResponse res) {
        utils.sendGifImage(res, from, content);
    }
}
