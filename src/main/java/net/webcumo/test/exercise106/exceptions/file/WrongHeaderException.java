package net.webcumo.test.exercise106.exceptions.file;

import net.webcumo.test.exercise106.exceptions.WrongFormatException;

public class WrongHeaderException extends WrongFormatException {
    public WrongHeaderException(String header) {
        super("The header of file is wrong, should be:\n " + header);
    }
}
