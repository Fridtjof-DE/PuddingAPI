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
        disableSysOut();
    }

    public Logger(String threadName, boolean debugMode) {
        this.threadName = threadName;
        this.debugMode = debugMode;
        disableSysOut();
    }

    public Logger(String threadName, boolean debugMode, boolean shut) {
        this.threadName = threadName;
        this.debugMode = debugMode;
        if(shut) {
            disableSysOut();
        }
    }

    public void info(String msg) {
        println("INFO", msg);
    }

    public void error(String msg) {
        println("ERROR", msg);
    }

    public void warn(String msg) {
        println("WARN", msg);
    }

    private void shut(String msg) {
        println("SHUT", msg);
    }

    public void debug(String msg) {
        if(debugMode) {
            println("DEBUG", msg);
        }
    }

    private void println(String type, String msg) {
        System.out.println("[" + dtf.format(now) + "] [" + threadName + "/" + type + "] " + msg);
    }

    private void disableSysOut() {
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override public void write(int b) {}
        }) {
            @Override public void flush() {}
            @Override public void close() {}
            @Override public void write(int b) {}
            @Override public void write(byte[] b) {}
            @Override public void write(byte[] buf, int off, int len) {}
            @Override public void print(boolean b) {}
            @Override public void print(char c) {}
            @Override public void print(int i) {}
            @Override public void print(long l) {}
            @Override public void print(float f) {}
            @Override public void print(double d) {}
            @Override public void print(char[] s) {}
            @Override public void print(String s) {}
            @Override public void print(Object obj) {}
            @Override public void println() {}
            @Override public void println(boolean x) { shut(x + ""); }
            @Override public void println(char x) { shut(x + ""); }
            @Override public void println(int x) { shut(x + ""); }
            @Override public void println(long x) { shut(x + ""); }
            @Override public void println(float x) { shut(x + ""); }
            @Override public void println(double x) { shut(x + ""); }
            @Override public void println(char[] x) { shut(x + ""); }
            @Override public void println(String x) { shut(x + ""); }
            @Override public void println(Object x) { shut(x + ""); }
            @Override public java.io.PrintStream printf(String format, Object... args) { return this; }
            @Override public java.io.PrintStream printf(java.util.Locale l, String format, Object... args) { return this; }
            @Override public java.io.PrintStream format(String format, Object... args) { return this; }
            @Override public java.io.PrintStream format(java.util.Locale l, String format, Object... args) { return this; }
            @Override public java.io.PrintStream append(CharSequence csq) { return this; }
            @Override public java.io.PrintStream append(CharSequence csq, int start, int end) { return this; }
            @Override public java.io.PrintStream append(char c) { return this; }
        });
    }
}