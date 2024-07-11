package net.webcumo.test.exercise106.parser;

import net.webcumo.test.exercise106.exceptions.file.FileIOException;
import net.webcumo.test.exercise106.exceptions.file.FileIsEmptyException;
import net.webcumo.test.exercise106.exceptions.file.FileIsTooLongException;
import net.webcumo.test.exercise106.exceptions.file.WrongHeaderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeFileParserTest {
    @BeforeEach
    public void setUp() {
        System.setProperty("max_lines", "1000");
    }


    @Test
    void givenNoFileThenException() {
        String fileName = "no such file.csv";
        EmployeeFileParser parser = new EmployeeFileParser(fileName);
        assertThrows(FileIOException.class, parser::getStrings);
    }

    @Test
    void givenCorrectFileThenGetStream() {
        assertEquals(5, new EmployeeFileParser("src/test/resources/sample.csv").getStrings().count());
    }

    @Test
    void givenFileWithoutHeaderThenException() {
        EmployeeFileParser parser = new EmployeeFileParser("src/test/resources/no-headers.csv");
        assertThrows(WrongHeaderException.class, parser::getStrings);
    }

    @Test
    void givenFileWithOnlyHeaderThenException() {
        EmployeeFileParser parser = new EmployeeFileParser("src/test/resources/headers.csv");
        assertThrows(FileIsEmptyException.class, parser::getStrings);
    }

    @Test
    void givenFileTooLongThenException() {
        System.setProperty("max_lines", "3");
        EmployeeFileParser parser = new EmployeeFileParser("src/test/resources/long.csv");
        assertThrows(FileIsTooLongException.class, parser::getStrings);
    }
}