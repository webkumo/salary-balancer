package net.webcumo.test.exercise106.exceptions.builder;

import net.webcumo.test.exercise106.exceptions.DataProcessingException;
import net.webcumo.test.exercise106.exceptions.ErrorCodes;

public class NoCeoFoundException extends DataProcessingException {
    public NoCeoFoundException() {
        super("CEO was not found in the file", ErrorCodes.NO_CEO.getErrorCode());
    }
}
