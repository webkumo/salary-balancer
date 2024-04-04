package net.webcumo.test.exercise106.exceptions;

public class WrongFormatException extends DataProcessingException {
    public WrongFormatException(String s) {
        super(s, ErrorCodes.WRONG_FORMAT.getErrorCode());
    }
}
