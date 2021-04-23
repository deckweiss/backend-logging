package at.deckweiss.logging;

import org.slf4j.MDC;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

class LoggerImpl implements Logger {
    private static final String METHODNAME_KEY = "MethodName";

    private final org.slf4j.Logger slf4jLogger;

    public LoggerImpl(org.slf4j.Logger slf4jLogger) {
        this.slf4jLogger = slf4jLogger;
    }

    @Override
    public void debug(String message, LogParam... params) {
        this.log(this.slf4jLogger::debug, message, params);
    }

    @Override
    public void debug(String message, LogParams params) {
        this.debug(message, params.toArray());
    }

    @Override
    public void debug(String message, Exception e, LogParam... params) {
        this.log(this.slf4jLogger::debug, message, e, params);
    }

    @Override
    public void debug(String message, Exception e, LogParams params) {
        this.debug(message, e, params.toArray());
    }

    @Override
    public void info(String message, LogParam... params) {
        this.log(this.slf4jLogger::info, message, params);
    }

    @Override
    public void info(String message, LogParams params) {
        this.info(message, params.toArray());
    }

    @Override
    public void info(String message, Exception e, LogParam... params) {
        this.log(this.slf4jLogger::info, message, e, params);
    }

    @Override
    public void info(String message, Exception e, LogParams params) {
        this.info(message, e, params.toArray());
    }

    @Override
    public void warn(String message, LogParam... params) {
        this.log(this.slf4jLogger::warn, message, params);
    }

    @Override
    public void warn(String message, LogParams params) {
        this.warn(message, params.toArray());
    }

    @Override
    public void warn(String message, Exception e, LogParam... params) {
        this.log(this.slf4jLogger::warn, message, e, params);
    }

    @Override
    public void warn(String message, Exception e, LogParams params) {
        this.warn(message, e, params.toArray());
    }

    @Override
    public void error(String message, LogParam... params) {
        this.log(this.slf4jLogger::error, message, params);
    }

    @Override
    public void error(String message, LogParams params) {
        this.error(message, params.toArray());
    }

    @Override
    public void error(String message, Exception e, LogParam... params) {
        this.log(this.slf4jLogger::error, message, e, params);
    }

    @Override
    public void error(String message, Exception e, LogParams params) {
        this.error(message, e, params.toArray());
    }

    private void log(Consumer<String> logFunc, String message, LogParam... params) {
        String msg = this.buildMessage(message, params);
        this.putMdcArguments();
        logFunc.accept(msg);
        this.removeMdcArguments();
    }

    private void log(BiConsumer<String, Exception> logFunc, String message, Exception e, LogParam... params) {
        String msg = this.buildMessage(message, params);
        this.putMdcArguments();
        logFunc.accept(msg, e);
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
        MDC.put(METHODNAME_KEY, methodName);
    }

    private void removeMdcArguments() {
        MDC.remove(METHODNAME_KEY);
    }
}