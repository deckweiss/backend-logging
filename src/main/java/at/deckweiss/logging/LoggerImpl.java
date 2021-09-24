package at.deckweiss.logging;

import org.slf4j.MDC;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

class LoggerImpl implements Logger {
    private static final String METHOD_NAME_KEY = "MethodName";

    private final org.slf4j.Logger slf4jLogger;

    public LoggerImpl(org.slf4j.Logger slf4jLogger) {
        this.slf4jLogger = slf4jLogger;
    }

    // region debug

    @Override
    public void debug(String message) {
        this.debug(message, new LogParams());
    }

    @Override
    public void debug(String message, LogParams params) {
        this.log(this.slf4jLogger::debug, message, params.toArray());
    }

    @Override
    public void debug(String message, Throwable throwable) {
        this.debug(message, throwable, new LogParams());
    }

    @Override
    public void debug(String message, Throwable throwable, LogParams params) {
        this.log(this.slf4jLogger::debug, message, throwable, params.toArray());
    }

    // endregion

    // region info

    @Override
    public void info(String message) {
        this.info(message, new LogParams());
    }

    @Override
    public void info(String message, LogParams params) {
        this.log(this.slf4jLogger::info, message, params.toArray());
    }

    @Override
    public void info(String message, Throwable throwable) {
        this.info(message, throwable, new LogParams());
    }

    @Override
    public void info(String message, Throwable throwable, LogParams params) {
        this.log(this.slf4jLogger::info, message, throwable, params.toArray());
    }

    // endregion

    // region warn

    @Override
    public void warn(String message) {
        this.warn(message, new LogParams());
    }

    @Override
    public void warn(String message, LogParams params) {
        this.log(this.slf4jLogger::warn, message, params.toArray());
    }

    @Override
    public void warn(String message, Throwable throwable) {
        this.warn(message, throwable, new LogParams());
    }

    @Override
    public void warn(String message, Throwable throwable, LogParams params) {
        this.log(this.slf4jLogger::warn, message, throwable, params.toArray());
    }

    // endregion

    // region error

    @Override
    public void error(String message) {
        this.error(message, new LogParams());
    }

    @Override
    public void error(String message, LogParams params) {
        this.log(this.slf4jLogger::error, message, params.toArray());
    }

    @Override
    public void error(String message, Throwable throwable) {
        this.error(message, throwable, new LogParams());
    }

    @Override
    public void error(String message, Throwable throwable, LogParams params) {
        this.log(this.slf4jLogger::error, message, throwable, params.toArray());
    }

    // endregion

    // region helper

    private void log(Consumer<String> logFunc, String message, LogParam... params) {
        String msg = this.buildMessage(message, params);
        this.putMdcArguments();
        logFunc.accept(msg);
        this.removeMdcArguments();
    }

    private void log(BiConsumer<String, Throwable> logFunc, String message, Throwable throwable, LogParam... params) {
        String msg = this.buildMessage(message, params);
        this.putMdcArguments();
        logFunc.accept(msg, throwable);
        this.removeMdcArguments();
    }

    private String buildMessage(String message, LogParam[] params) {
        String format = "%s %s";
        return params != null && params.length > 0 ? String.format(format, message, this.buildParamList(params)) : message;
    }

    private String buildParamList(LogParam[] params) {
        if (params.length == 0) {
            return "";
        }

        return "[" + Arrays.stream(params).map(LogParam::toString).collect(Collectors.joining(", ")) + "]";
    }

    private void putMdcArguments() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String methodName = Arrays.stream(stackTrace).skip(1)
                                  .filter(s -> !s.getClassName().equals(LoggerImpl.class.getName())).map(StackTraceElement::getMethodName)
                                  .findFirst()
                                  .orElse("method-not-found");
        MDC.put(METHOD_NAME_KEY, methodName);
    }

    private void removeMdcArguments() {
        MDC.remove(METHOD_NAME_KEY);
    }

    // endregion
}