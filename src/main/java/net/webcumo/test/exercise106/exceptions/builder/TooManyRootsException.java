package net.webcumo.test.exercise106.exceptions.builder;

import net.webcumo.test.exercise106.exceptions.DataProcessingException;
import net.webcumo.test.exercise106.exceptions.ErrorCodes;
import net.webcumo.test.exercise106.tree.TreeValue;

public class TooManyRootsException extends DataProcessingException {
    private static final String ERROR_MESSAGE = "Found more than one %ss, the first %s, the second %s.";

    public TooManyRootsException(String rootName, TreeValue ceo1, TreeValue ceo2) {
        super(ERROR_MESSAGE.formatted(rootName, ceo1, ceo2), ErrorCodes.TOO_MANY_CEOS.getErrorCode());
    }
}
