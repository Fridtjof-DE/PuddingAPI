package tk.fridtjof.puddingapi.general.io;

import tk.fridtjof.puddingapi.general.utils.Logger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class FileAPI {

    private static Logger logger;

    public static void createDirectory(String path) {
        createDirectory(path, false);
    }

    public static void createDirectory(String path, boolean debug) {
        logger  = new Logger("PuddingAPI/FileAPI", debug);

        File file = new File(path);
        if(!file.exists()){
            logger.info("Creating directory " + path + "...");
            file.mkdir();
        }
    }

    public static void saveFile(String webFile, String localFile, boolean debug) {

        try {
            logger.info("Starting download of " + webFile);

            logger.debug("Installing " + webFile + " to " + localFile + "...");
            URL url = new URL(webFile);

            logger.debug("Openning Stream...");
            InputStream in = url.openStream();

            logger.debug("Setting Output Stream...");
            FileOutputStream fos = new FileOutputStream(new File(localFile));

            int length = -1;
            byte[] buffer = new byte[1024];

            logger.debug("Reading bytes...");
            while ((length = in.read(buffer)) > -1) {
                fos.write(buffer, 0, length);
            }

            logger.debug("Closing Streams...");
            fos.close();
            in.close();
        } catch(IOException ioException) {
            logger.error("Could");
            ioException.printStackTrace();
        }
    }
}
