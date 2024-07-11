package net.webcumo.test.exercise106;

import net.webcumo.test.exercise106.exceptions.DataProcessingException;

public interface ErrorCodeListener {
    void registerErrorCode(DataProcessingException exceptionWithCode);
}
