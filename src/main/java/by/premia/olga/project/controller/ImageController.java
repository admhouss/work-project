package by.premia.olga.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author Vlad Abramov
 */
@Controller
@RequestMapping(value = "/image", method = RequestMethod.GET)
public class ImageController {

    @RequestMapping("error")
    public void errorImages(@RequestParam int code, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("image/jpeg");
        ServletOutputStream outputStream = res.getOutputStream();

//        int index = Integer.parseInt(req.getParameter("index"));
//        ImageArray ia = ImageArray.getInstance();
//        Image image = ia.getImages().get(index);
        String url = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
        System.out.println(url.substring(5, url.indexOf("WEB-INF")) + "assets/img/" + code + "_man.jpg");
        ImageIO.write(ImageIO.read(new File(url.substring(5, url.indexOf("WEB-INF")) + "assets/img/" + code + "_man.jpg")), "JPEG", outputStream);
    }
}
