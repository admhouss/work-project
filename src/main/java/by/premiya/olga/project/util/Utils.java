package by.premiya.olga.project.util;

import by.premiya.olga.project.entity.Image;
import by.premiya.olga.project.entity.Wheel;
import by.premiya.olga.project.service.ImageService;
import by.premiya.olga.project.service.ProductService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author vabramov
 */
@Component
public final class Utils {

    private Logger logger = LoggerFactory.getLogger(Utils.class);

    @Autowired
    private ImageService imageService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private String OS = System.getProperty("os.name").toLowerCase();
    private String classPath = Utils.class.getProtectionDomain().getCodeSource().getLocation().toString();
    private String imagePath = classPath.substring(5, classPath.indexOf("WEB-INF")) + "assets/img/";;

//    {
//        imagePath = classPath.substring(5, classPath.indexOf("WEB-INF")) + "assets/img/";
//    }

    private Utils() {
    }

    public boolean sendImage(HttpServletResponse res, Integer imageId) {
        ServletOutputStream outputStream = null;
        Image image = imageService.getById(imageId);
        if (image == null) return sendDefaultImage(res);
        try {
            res.setContentType(image.getContentType());
            outputStream = res.getOutputStream();
            return ImageIO.write(ImageIO.read(new File(image.getPath())), image.getType(), outputStream);
        } catch (IOException e) {
            logger.error("File '" + image.getPath() + "' not send");
            return sendDefaultImage(res);
        }
    }

    public boolean sendGifImage(HttpServletResponse res, String from, String content) {
        res.setContentType("image/gif");
        try {
            ServletOutputStream outputStream = res.getOutputStream();
            return ImageIO.write(ImageIO.read(new File(imagePath + from + "/" + content + ".gif")), "GIF", outputStream);
        } catch (IOException e) {
            logger.error("File '" + content + ".gif' not send");
            return sendDefaultImage(res);
        }
    }

    private boolean sendDefaultImage(HttpServletResponse res) {
        res.setContentType("image/jpeg");
        try {
            ServletOutputStream outputStream = res.getOutputStream();
            return ImageIO.write(ImageIO.read(new File(imagePath + "errors/image_not_available.jpg")), "JPEG", outputStream);
        } catch (IOException e1) {
            logger.error("Default image 'image_not_available.jpg' not send");
        }
        return false;
    }

    public boolean uploadImage(HttpServletRequest request, String productName, String producer, String model) {
        boolean isMultipart; String filePath = ""; int maxFileSize = 50 * 1024; int maxMemSize = 4 * 1024;
        isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            return false;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemSize);
        if (isWindows()) {
            filePath = "D:\\Files\\work-project\\" + productName +"\\";
        } else if (isUnix()) {
            filePath = "/home/vlad/Pictures/work-project/" + productName + "/";
        }
        factory.setRepository(new File(filePath));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(maxFileSize);
        Object product = productService.getProductByModel(productName, model);
        Image existingImage;
        try {
            Field imageId = product.getClass().getDeclaredField("imageId");
            imageId.setAccessible(true);
            existingImage = imageService.getById((Integer)imageId.get(product));
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
            return false;
        }
        try {
            List fileItems = upload.parseRequest(request);

            for (Object fileItem : fileItems) {
                FileItem fi = (FileItem) fileItem;
                if (!fi.isFormField()) {
                    String fileName = encodeName(fi.getName(), product);
                    String contentType = fi.getContentType();
                    File file = new File(filePath + fileName);
                    Image image = new Image(getImageType(fileName), contentType, file.getPath());
                    if (existingImage != null) {
                        File oldFile = new File(existingImage.getPath());
                        oldFile.delete();
                        existingImage.setContentType(image.getContentType());
                        existingImage.setPath(image.getPath());
                        existingImage.setType(image.getType());
                        image = existingImage;
                    }
                    imageService.save(image);
                    productService.updateProduct(setImageId(product,image.getId()));
                    fi.write(file);
                    fi.delete();
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }

    private String encodeName(String name, Object product) {
        String salt = "";
        if (product instanceof Wheel) {
            salt = String.valueOf(((Wheel) product).getId());
        }
        String extension = name.substring(name.lastIndexOf('.'));
        String fileName = name.substring(0, name.lastIndexOf('.'));
        return passwordEncoder.encodePassword(fileName, salt) + extension;
    }

    private Object setImageId(Object product, Integer imageId) {
        if (product instanceof Wheel) {
            ((Wheel)product).setImageId(imageId);
        }
        return product;
    }


    public String getImageType(String imageName) {
        String extension = imageName.substring(imageName.lastIndexOf('.') + 1);
        if ("jpg".equalsIgnoreCase(extension)) {
            return "JPEG";
        } else if ("png".equalsIgnoreCase(extension)) {
            return "PNG";
        }
        return "JPEG";
    }

    public boolean isWindows() {
        return (OS.contains("win"));
    }

    public boolean isMac() {
        return (OS.contains("mac"));
    }

    public boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || (OS.contains("aix")));
    }

    public boolean isSolaris() {
        return (OS.contains("sunos"));
    }

    public boolean hasCookie(HttpServletRequest req, String cookieName) {
        if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals(cookieName)) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("In request found cookie '" + cookieName + "'");
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public void setLogoutCookie(HttpServletResponse response) {
        response.addCookie(new Cookie("loc", null));
//        Cookie lic = new Cookie("lic", null);
//        lic.setMaxAge(0);
//        response.addCookie(lic);
    }

    public void removeLogoutCookie(HttpServletResponse response) {
//        response.addCookie(new Cookie("lic",null));
        Cookie cookie = new Cookie("loc", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
