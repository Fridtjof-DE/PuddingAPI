package me.fridtjof.puddingapi.general.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker {

    private Logger logger = LoggerFactory.getLogger(UpdateChecker.class);
    private String currentVersion;
    private String checkURL;
    private String updateURL;
    private String newVersion;
    private String errorMessage = "Update checker failed!";
    private String updateMessage;
    private final long CHECK_INTERVAL = 12_000; //In ticks.

    public UpdateChecker(String currentVersion, String checkURL, String updateURL) {
        this.currentVersion = currentVersion;
        this.checkURL = checkURL;
        this.updateURL = updateURL;
        checkForUpdate();
    }

    public void checkForUpdate() {
        try {
            final HttpsURLConnection connection = (HttpsURLConnection) new URL(checkURL).openConnection();
            connection.setRequestMethod("GET");
            newVersion = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
        } catch (final IOException e) {
            logger.error(errorMessage);
            e.printStackTrace();
            return;
        }

        updateMessage = "Version " + newVersion + " is available at: " + updateURL + " - " + currentVersion + " is installed!";

        VersionComparer versionComparer = new VersionComparer(currentVersion, newVersion);

        if(versionComparer.newVersionAvailable()) {
            return;
        }

        /*try {
            int newV = Integer.parseInt(newVersion.replaceAll("\\.", ""));
            int thisV = Integer.parseInt(currentVersion.replaceAll("\\.", ""));
            if (newV <= thisV) {
                return;
            }

        } catch(NumberFormatException exception) {
            if (currentVersion.equals(newVersion)) return;
        }*/

        logger.warn(updateMessage);


    }
}