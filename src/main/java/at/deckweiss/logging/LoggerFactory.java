package at.deckweiss.logging;

public class LoggerFactory {
    private LoggerFactory() {
    }

    public static Logger getLogger(final Class<?> clazz) {
        return new LoggerImpl(org.slf4j.LoggerFactory.getLogger(clazz));
    }

    public static Logger getLogger(String name) {
        return new LoggerImpl(org.slf4j.LoggerFactory.getLogger(name));
    }
}