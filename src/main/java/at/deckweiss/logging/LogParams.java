package at.deckweiss.logging;

import java.util.ArrayList;
import java.util.List;

public class LogParams {
    private final List<LogParam> logParams;

    public LogParams() {
        this.logParams = new ArrayList<>();
    }

    public LogParams(String name, Object value) {
        this();
        this.logParams.add(new LogParam(name, value));
    }

    public LogParams add(String name, Object value) {
        this.logParams.add(new LogParam(name, value));
        return this;
    }

    public LogParams addIfNotNull(String name, Object value) {
        if (value != null) {
            this.logParams.add(new LogParam(name, value));
        }

        return this;
    }

    LogParam[] toArray() {
        return this.logParams.toArray(new LogParam[] {});
    }
}