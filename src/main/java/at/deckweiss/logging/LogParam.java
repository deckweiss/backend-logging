package at.deckweiss.logging;

import lombok.Getter;

@Getter
public class LogParam {
    private final String paramName;
    private final Object paramValue;

    public LogParam(String paramName, Object paramValue) {
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    @Override
    public String toString() {
        return String.format("%s=\"%s\"", this.paramName, this.paramValue == null ? "null" : this.paramValue.toString());
    }
}