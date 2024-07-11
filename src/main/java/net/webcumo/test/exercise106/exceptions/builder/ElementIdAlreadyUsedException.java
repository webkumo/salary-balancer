package net.webcumo.test.exercise106.exceptions.builder;

import net.webcumo.test.exercise106.exceptions.DataProcessingException;
import net.webcumo.test.exercise106.exceptions.ErrorCodes;
import net.webcumo.test.exercise106.tree.TreeValue;

public class ElementIdAlreadyUsedException extends DataProcessingException {
    private static final String ERROR_MESSAGE = "Cannot add %s. The %s is already registered.";

    public ElementIdAlreadyUsedException(TreeValue employee, TreeValue alreadyRegistered) {
        super(ERROR_MESSAGE.formatted(employee, alreadyRegistered),
                ErrorCodes.ALREADY_USED.getErrorCode());
    }
}
