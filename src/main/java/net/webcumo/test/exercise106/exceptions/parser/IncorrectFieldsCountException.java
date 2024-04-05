package net.webcumo.test.exercise106.exceptions.parser;

import net.webcumo.test.exercise106.exceptions.WrongFormatException;

public class IncorrectFieldsCountException extends WrongFormatException {
    private static final String ERROR_MESSAGE = "The string (%s) contains different (%s) count of fields.";

    public IncorrectFieldsCountException(String employee, int count) {
        super(ERROR_MESSAGE.formatted(employee, count));
    }
}
