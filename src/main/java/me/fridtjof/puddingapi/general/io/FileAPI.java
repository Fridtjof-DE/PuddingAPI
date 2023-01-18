package me.fridtjof.puddingapi.general.io;

import java.io.*;
import java.net.URL;

public class FileAPI {

    private Logger logger;

    public FileAPI(Logger logger) {
        this.logger = logger;
    }
    public void createDirectory(String path) {

        File file = new File(path);
        if(!file.exists()){
            logger.info("Creating directory " + path + "...");
            file.mkdir();
        }
    }

    public void createDirectories(String path) {

        File file = new File(path);
        if(!file.exists()){
            logger.info("Creating directory " + path + "...");
            file.mkdirs();
        }
    }

    public void saveFileFromWeb(String webFile, String localFile) {

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
            logger.error("Failed!");
            ioException.printStackTrace();
        }
    }
}
