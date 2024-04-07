package net.webcumo.test.exercise106.exceptions.builder;

import net.webcumo.test.exercise106.employee.Employee;
import net.webcumo.test.exercise106.exceptions.DataProcessingException;
import net.webcumo.test.exercise106.exceptions.ErrorCodes;

public class EmployeeIdAlreadyUsedException extends DataProcessingException {
    private static final String ERROR_MESSAGE = "Cannot add user %s. The user with id %s is already registered: %s.";

    public EmployeeIdAlreadyUsedException(Employee employee, Employee alreadyRegistered) {
        super(ERROR_MESSAGE.formatted(employee, employee.id(), alreadyRegistered),
                ErrorCodes.ALREADY_USED.getErrorCode());
    }
}
