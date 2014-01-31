package by.premiya.olga.project.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @author vabramov
 */
public final class Utils {

    private static Logger logger = LoggerFactory.getLogger(Utils.class);

    private Utils() {
    }

    public static boolean sendImage(HttpServletResponse res, String from, String content) {
        res.setContentType("image/jpeg");
        String url = Utils.class.getProtectionDomain().getCodeSource().getLocation().toString();
        url = url.substring(5, url.indexOf("WEB-INF")) + "assets/img/";
        ServletOutputStream outputStream = null;
        try {
            outputStream = res.getOutputStream();
            return ImageIO.write(ImageIO.read(new File(url + from + "/" + content)), getImageType(content), outputStream);
        } catch (IOException e) {
            logger.error("File '" + url + "' not send");
            return sendDefaultImage(url, outputStream);
        }
    }

    public static boolean sendGifImage(HttpServletResponse res, String from, String content) {
        res.setContentType("image/gif");
        String url = Utils.class.getProtectionDomain().getCodeSource().getLocation().toString();
        url = url.substring(5, url.indexOf("WEB-INF")) + "assets/img/";
        ServletOutputStream outputStream = null;
        try {
            outputStream = res.getOutputStream();
            return ImageIO.write(ImageIO.read(new File(url + from + "/" + content + ".gif")), "GIF", outputStream);
        } catch (IOException e) {
            logger.error("File '" + url + "' not send");
            return sendDefaultImage(url, outputStream);
        }
    }

    private static boolean sendDefaultImage(String url, ServletOutputStream outputStream) {
        try {
            return ImageIO.write(ImageIO.read(new File(url + "errors/image_not_available.jpg")), "JPEG", outputStream);
        } catch (IOException e1) {
            logger.error("Default image '" + url + "' not send");
        }
        return false;
    }

    public static void uploadImage(HttpServletRequest request, String from, String content) {
        //todo write this method
         boolean isMultipart;
         String filePath;
         int maxFileSize = 50 * 1024;
         int maxMemSize = 4 * 1024;

            // Check that we have a file upload request
            isMultipart = ServletFileUpload.isMultipartContent(request);

            DiskFileItemFactory factory = new DiskFileItemFactory();
            // maximum size that will be stored in memory
            factory.setSizeThreshold(maxMemSize);
            // Location to save data that is larger than maxMemSize.
            factory.setRepository(new File("c:\\temp"));

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // maximum file size to be uploaded.
            upload.setSizeMax( maxFileSize );

            try{
                // Parse the request to get file items.
                List fileItems = upload.parseRequest(request);

                // Process the uploaded file items
                Iterator i = fileItems.iterator();

                while ( i.hasNext () )
                {
                    FileItem fi = (FileItem)i.next();
                    if ( !fi.isFormField () )
                    {
                        // Get the uploaded file parameters
                        String fieldName = fi.getFieldName();
                        String fileName = fi.getName();
                        String contentType = fi.getContentType();
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();
                        // Write the file
                        if( fileName.lastIndexOf("\\") >= 0 ){
                            file = new File( filePath +
                                    fileName.substring( fileName.lastIndexOf("\\"))) ;
                        }else{
                            file = new File( filePath +
                                    fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                        }
                        fi.write( file ) ;
                        out.println("Uploaded Filename: " + fileName + "<br>");
                    }
                }
                out.println("</body>");
                out.println("</html>");
            }catch(Exception ex) {
                System.out.println(ex);
            }
    }


    public static boolean hasCookie(HttpServletRequest req, String cookieName) {
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

    public static void setLogoutCookie(HttpServletResponse response) {
        response.addCookie(new Cookie("loc", null));
//        Cookie lic = new Cookie("lic", null);
//        lic.setMaxAge(0);
//        response.addCookie(lic);
    }

    public static void removeLogoutCookie(HttpServletResponse response) {
//        response.addCookie(new Cookie("lic",null));
        Cookie cookie = new Cookie("loc", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public static String getImageType(String imageName) {
        String extension = imageName.substring(imageName.lastIndexOf('.'));
        if ("jpg".equalsIgnoreCase(extension)) {
            return "JPEG";
        } else if ("png".equalsIgnoreCase(extension)) {
            return "PNG";
        }
        return "JPEG";
    }
}
