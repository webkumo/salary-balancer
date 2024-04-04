package net.webcumo.test.exercise106.parser;

import net.webcumo.test.exercise106.exceptions.file.FileIOException;
import net.webcumo.test.exercise106.exceptions.file.FileIsEmptyException;
import net.webcumo.test.exercise106.exceptions.file.WrongHeaderException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeFileParserTest {

    @Test
    void givenNoFileThenException() {
        String fileName = "no such file.csv";
        try {
            new EmployeeFileParser(fileName).getStrings();
            fail();
        } catch (FileIOException e) {
            assertTrue(e.getMessage().contains(fileName));
        }
    }

    @Test
    void givenCorrectFileThenGetStream() {
        assertEquals(5, new EmployeeFileParser("src/test/resources/sample.csv").getStrings().count());
    }

    @Test
    void givenFileWithoutHeaderThenException() {
        try {
            new EmployeeFileParser("src/test/resources/no-headers.csv").getStrings();
            fail();
        } catch (WrongHeaderException e) {
            assertTrue(e.getMessage().startsWith("The header of file is wrong, should be:\n "));
        }
    }

    @Test
    void givenFileWithOnlyHeaderThenException() {
        try {
            new EmployeeFileParser("src/test/resources/headers.csv").getStrings();
            fail();
        } catch (FileIsEmptyException e) {
            assertEquals("There is nothing to parse in the file", e.getMessage());
        }
    }
}