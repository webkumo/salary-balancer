package net.webcumo.test.exercise106.exceptions.builder;

import net.webcumo.test.exercise106.exceptions.DataProcessingException;
import net.webcumo.test.exercise106.exceptions.ErrorCodes;

import java.util.List;

public class OrphansFoundException extends DataProcessingException {

    private static final String ERROR_MESSAGE = """
        There was found orphans in file, cannot process:
        %s
        """;

    public OrphansFoundException(List<String> orphans) {
        super(ERROR_MESSAGE.formatted(orphans), ErrorCodes.ORPHANS.getErrorCode());
    }
}
