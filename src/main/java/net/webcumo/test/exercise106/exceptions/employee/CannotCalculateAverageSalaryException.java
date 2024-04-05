package net.webcumo.test.exercise106.exceptions.employee;

import net.webcumo.test.exercise106.employee.Employee;
import net.webcumo.test.exercise106.exceptions.WrongFormatException;

public class CannotCalculateAverageSalaryException extends WrongFormatException {
    private static final String ERROR_MESSAGE = "Cannot calculate average salary for subordinates of the %s";
    public CannotCalculateAverageSalaryException(Employee employee) {
        super(ERROR_MESSAGE.formatted(employee));
    }
}
