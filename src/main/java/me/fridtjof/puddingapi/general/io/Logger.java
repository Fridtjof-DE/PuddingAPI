package me.fridtjof.puddingapi.general.io;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private boolean debugMode;
    private String classPrefix = "";
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Logger(boolean debugMode) {
        this.debugMode = debugMode;
    }
    public Logger(String classPrefix, boolean debugMode) {
        this.classPrefix = "[" + classPrefix + "] ";
        this.debugMode = debugMode;
    }

    public void info(String message) {
        printer("INFO", message);
    }

    public void debug(String message) {
        printer("DEBUG", message);
    }

    public void warn(String message) {
        printer("WARN", message);
    }

    public void error(String message) {
        printer("ERROR", message);
    }

    private void printer(String prefix, String message) {
        System.out.println("[" + dtf.format(LocalDateTime.now()) + "] [" + prefix + "]: " + classPrefix + message);
    }

    //setters & getters

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}