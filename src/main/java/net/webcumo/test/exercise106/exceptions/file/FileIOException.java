package net.webcumo.test.exercise106.exceptions.file;

import net.webcumo.test.exercise106.exceptions.DataProcessingException;
import net.webcumo.test.exercise106.exceptions.ErrorCodes;

import java.io.IOException;

public class FileIOException extends DataProcessingException {
    public FileIOException(IOException e) {
        super(e.getMessage(), ErrorCodes.FILE_NOT_FOUND.getErrorCode());
    }
}
