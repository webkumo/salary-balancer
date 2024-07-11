package net.webcumo.test.exercise106;

import net.webcumo.test.exercise106.exceptions.DataProcessingException;

public class ExitOnErrorCode implements ErrorCodeListener {
    @Override
    public void registerErrorCode(DataProcessingException exceptionWithErrorCode) {
        //todo it is impossible to test `System.exit` calls since java 17.
        System.exit(exceptionWithErrorCode.getErrorCode());
    }
}
