package net.webcumo.test.exercise106.exceptions.builder;

import net.webcumo.test.exercise106.exceptions.DataProcessingException;
import net.webcumo.test.exercise106.exceptions.ErrorCodes;

public class NoRootFoundException extends DataProcessingException {
    public NoRootFoundException(String rootFieldName) {
        super("%s was not found in the file".formatted(rootFieldName),
                ErrorCodes.NO_CEO.getErrorCode());
    }
}
