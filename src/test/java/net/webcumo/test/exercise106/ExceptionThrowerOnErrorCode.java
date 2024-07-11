package net.webcumo.test.exercise106;

import net.webcumo.test.exercise106.exceptions.DataProcessingException;

public class ExceptionThrowerOnErrorCode implements ErrorCodeListener {
    @Override
    public void registerErrorCode(DataProcessingException exception) {
        throw exception;
    }
}
