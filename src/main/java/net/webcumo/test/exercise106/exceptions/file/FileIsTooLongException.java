package net.webcumo.test.exercise106.exceptions.file;

import net.webcumo.test.exercise106.exceptions.WrongFormatException;

public class FileIsTooLongException extends WrongFormatException {
    public FileIsTooLongException(int maxLines) {
        super("The file contains more lines than configured as maximum(%s)".formatted(maxLines));
    }
}
