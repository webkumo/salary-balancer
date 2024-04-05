package net.webcumo.test.exercise106.parser;

import net.webcumo.test.exercise106.exceptions.file.FileIOException;
import net.webcumo.test.exercise106.exceptions.file.FileIsEmptyException;
import net.webcumo.test.exercise106.exceptions.file.FileIsTooLongException;
import net.webcumo.test.exercise106.exceptions.file.WrongHeaderException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class EmployeeFileParser implements EmployeeStringsSource {
    private static final String HEADER = "Id,firstName,lastName,salary,managerId";

    private final String fileName;
    private final int maxLines;

    public EmployeeFileParser(String fileName) {
        this.fileName = fileName;
        String maxLinesStr = System.getProperty("max_lines");
        this.maxLines = maxLinesStr == null
                ? 1000
                : Integer.parseInt(maxLinesStr);
    }

    @Override
    public Stream<String> getStrings() {
        try {
            List<String> strings = Files.readAllLines(Path.of(fileName));
            validate(strings);
            strings.removeFirst();
            return strings.stream();
        } catch (IOException e) {
            throw new FileIOException(e);
        }
    }

    private void validate(List<String> strings) {
        if (strings.size() < 2) {
            throw new FileIsEmptyException();
        }
        String header = strings.getFirst();
        if (!header.equals(HEADER)) {
            throw new WrongHeaderException(HEADER);
        }
        if (strings.size() > maxLines + 1) {
            throw new FileIsTooLongException(maxLines);
        }
    }
}
