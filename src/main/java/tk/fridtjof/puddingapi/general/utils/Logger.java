package tk.fridtjof.puddingapi.general.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    private LocalDateTime now = LocalDateTime.now();

    private String threadName = "Unknown Thead";
    private boolean debugMode = false;

    public Logger(String threadName) {
        this.threadName = threadName;
    }

    public Logger(String threadName, boolean debugMode) {
        this.threadName = threadName;
        this.debugMode = debugMode;
    }

    public void info(String msg) {
        print("INFO", msg);
    }

    public void error(String msg) {
        print("ERROR", msg);
    }

    public void warn(String msg) {
        print("WARN", msg);
    }

    public void debug(String msg) {
        if(debugMode) {
            print("DEBUG", msg);
        }
    }

    private void print(String type, String msg) {
        System.out.println("[" + dtf.format(now) + "] [" + threadName + "/" + type + "] " + msg);
    }
}