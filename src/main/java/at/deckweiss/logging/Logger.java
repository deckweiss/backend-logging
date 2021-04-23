package at.deckweiss.logging;

public interface Logger {
    void debug(String message, LogParam... params);
    void debug(String message, LogParams params);
    void debug(String message, Exception e, LogParam... params);
    void debug(String message, Exception e, LogParams params);

    void info(String message, LogParam... params);
    void info(String message, LogParams params);
    void info(String message, Exception e, LogParam... params);
    void info(String message, Exception e, LogParams params);

    void warn(String message, LogParam... params);
    void warn(String message, LogParams params);
    void warn(String message, Exception e, LogParam... params);
    void warn(String message, Exception e, LogParams params);

    void error(String message, LogParam... params);
    void error(String message, LogParams params);
    void error(String message, Exception e, LogParam... params);
    void error(String message, Exception e, LogParams params);
}