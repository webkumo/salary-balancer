package net.webcumo.test.exercise106.exceptions;

public enum ErrorCodes {
    TOO_MANY_CEOS(0x0001),
    ALREADY_USED(0x0002),
    WRONG_FORMAT(0x0003),
    FILE_NOT_FOUND(0x0004),
    NO_CEO(0x0005),
    ORPHANS(0x0006)
    ;
    private final int errorCode;

    ErrorCodes(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
