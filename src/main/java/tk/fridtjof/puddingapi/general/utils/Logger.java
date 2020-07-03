package tk.fridtjof.puddingapi.general.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    private LocalDateTime now = LocalDateTime.now();

    private String threadName = "Unknown Thead";
    private boolean debugMode = false;

    public Logger(String threadName, boolean debugMode) {
        this.threadName = threadName;
        this.debugMode = debugMode;
    }

    public void info(String msg) {
        System.out.println("[" + dtf.format(now) + "] [" + threadName + "/INFO] " + msg);
    }

    public void error(String msg) {
        System.out.println("[" + dtf.format(now) + "] [" + threadName + "/ERROR] " + msg);
    }

    public void debug(String msg) {
        if(debugMode) {
            System.out.println("[" + dtf.format(now) + "] [" + threadName + "/DEBUG] " + msg);
        }
    }
}