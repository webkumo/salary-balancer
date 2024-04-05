package net.webcumo.test.exercise106.exceptions.file;

import net.webcumo.test.exercise106.exceptions.WrongFormatException;

public class FileIsEmptyException extends WrongFormatException {
    public FileIsEmptyException() {
        super("There is nothing to parse in the file");
    }
}
