package at.deckweiss.logging;

public interface Logger {
    void debug(String message);
    void debug(String message, LogParams params);
    void debug(String message, Throwable throwable);
    void debug(String message, Throwable throwable, LogParams params);

    void info(String message);
    void info(String message, LogParams params);
    void info(String message, Throwable throwable);
    void info(String message, Throwable throwable, LogParams params);

    void warn(String message);
    void warn(String message, LogParams params);
    void warn(String message, Throwable throwable);
    void warn(String message, Throwable throwable, LogParams params);

    void error(String message);
    void error(String message, LogParams params);
    void error(String message, Throwable throwable);
    void error(String message, Throwable throwable, LogParams params);
}