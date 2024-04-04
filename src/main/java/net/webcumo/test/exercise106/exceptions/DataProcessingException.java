package net.webcumo.test.exercise106.exceptions;

public class DataProcessingException extends IllegalStateException {
    private final int errorCode;

    public DataProcessingException(String s, int errorCode) {
        super(s);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
