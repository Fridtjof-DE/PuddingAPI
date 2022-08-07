package me.fridtjof.puddingapi.general.utils;

public class VersionComparer {

    //W.I.P. TODO

    String installedVersion;
    String installedVersionCore;
    String installedVersionSnapshot;
    String availableVersion;
    String availableVersionCore;
    String availableVersionSnapshot;
    boolean isSnapshotVersion;

    public VersionComparer(String installedVersion, String availableVersion) {
        this.installedVersion = installedVersion;
        this.availableVersion = availableVersion;
    }

    public boolean newVersionAvailable() {

        /*installedVersionCore = installedVersion.replace("-SNAPSHOT-[^\\d]+", "");
        availableVersionCore = availableVersion.replace("-SNAPSHOT-[^\\d]+", "");

        if(availableVersionCore.contains("SNAPSHOT")) {

            installedVersionSnapshot = installedVersion.replaceAll("-", "");
            installedVersionSnapshot = installedVersion.replace("[^\\d]+(SNAPSHOT)", "");

            availableVersionSnapshot = availableVersion.replaceAll("-", "");
            availableVersionSnapshot = availableVersion.replace("[^\\d]+(SNAPSHOT)", "");

            if(Integer.parseInt(availableVersionSnapshot) > Integer.parseInt(installedVersionSnapshot)) {
                return true;
            }
        }

        while(!availableVersionCore.equals("")) {

            if(Integer.parseInt(availableVersionCore.formatted("^[\d]*")) > Integer.parseInt(installedVersionCore.formatted("^[\d]*")) {
                return true;
            }

            availableVersionCore = availableVersionCore.replace("^[\d]*\.?", "");
            installedVersionCore = installedVersionCore.replace("^[\d]*\.?", "");
        }*/

        return false;
    }
}
