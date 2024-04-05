package net.webcumo.test.exercise106.exceptions.builder;

import net.webcumo.test.exercise106.employee.Employee;
import net.webcumo.test.exercise106.exceptions.DataProcessingException;
import net.webcumo.test.exercise106.exceptions.ErrorCodes;

public class TooManyCeosException extends DataProcessingException {
    private static final String ERROR_MESSAGE = "Found more than one CEOs, the first %s, the second %s.";

    public TooManyCeosException(Employee ceo1, Employee ceo2) {
        super(ERROR_MESSAGE.formatted(ceo1, ceo2), ErrorCodes.TOO_MANY_CEOS.getErrorCode());
    }
}
