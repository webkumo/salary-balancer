package net.webcumo.test.exercise106.exceptions.file;

import net.webcumo.test.exercise106.exceptions.DataProcessingException;
import net.webcumo.test.exercise106.exceptions.ErrorCodes;

public class FileIsTooLongException extends DataProcessingException {
    public FileIsTooLongException(int maxLines) {
        super("The file contains more lines than configured as maximum(%s)".formatted(maxLines),
                ErrorCodes.FILE_TOO_LONG.getErrorCode());
    }
}
