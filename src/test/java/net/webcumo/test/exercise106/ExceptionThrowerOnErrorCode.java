package net.webcumo.test.exercise106;

import net.webcumo.test.exercise106.exceptions.DataProcessingException;

public class ExceptionThrowerOnErrorCode implements ErrorCodeListener {
    @Override
    public void registerErrorCode(int code) {
        throw new DataProcessingException("Found error code", code);
    }
}
