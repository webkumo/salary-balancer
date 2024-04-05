package net.webcumo.test.exercise106.exceptions.parser;

import net.webcumo.test.exercise106.exceptions.WrongFormatException;

public class CannotParseNumberException extends WrongFormatException {
    private static final String ERROR_MESSAGE = "Cannot parse number: %s for string %s";
    public CannotParseNumberException(NumberFormatException e, String employee) {
        super(ERROR_MESSAGE.formatted(e.getMessage(), employee));
    }
}
