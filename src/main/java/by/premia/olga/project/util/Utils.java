package by.premia.olga.project.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author vabramov
 */
public final class Utils {
    private static Logger logger = LoggerFactory.getLogger(Utils.class);

    private Utils() {
    }

    public static boolean sendImage(HttpServletResponse res, String from, int content) {
        res.setContentType("image/jpeg");
        String url = Utils.class.getProtectionDomain().getCodeSource().getLocation().toString();
        url = url.substring(5, url.indexOf("WEB-INF")) + "assets/img/";
        ServletOutputStream outputStream = null;
        try {
            outputStream = res.getOutputStream();
            return ImageIO.write(ImageIO.read(new File(url + from + "/" + content + ".jpg")), "JPEG", outputStream);
        } catch (IOException e) {
            logger.error("File \'" + url + "\' not send");
            return sendDefaultImage(url, outputStream);
        }
    }
    private static boolean sendDefaultImage(String url, ServletOutputStream outputStream) {
        try {
            return ImageIO.write(ImageIO.read(new File(url + "errors/image_not_available.jpg")), "JPEG", outputStream);
        } catch (IOException e1) {
            logger.error("Default image \'" + url + "\' not send");
        }
        return false;
    }
}
