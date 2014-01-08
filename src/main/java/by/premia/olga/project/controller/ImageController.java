package by.premia.olga.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Vlad Abramov
 */
@Controller
@RequestMapping(value = "/image", method = RequestMethod.GET)
public class ImageController {

    @RequestMapping("error")
    public void errorImages(@RequestParam int code, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/assets/img/"+code+"-man.jsp");
        rd.forward(req, res);
    }
}
