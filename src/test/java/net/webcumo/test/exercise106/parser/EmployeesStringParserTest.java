package net.webcumo.test.exercise106.parser;

import net.webcumo.test.exercise106.employee.Employee;
import net.webcumo.test.exercise106.exceptions.parser.CannotParseNumberException;
import net.webcumo.test.exercise106.exceptions.parser.IncorrectFieldsCountException;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EmployeesStringParserTest {
    @Test
    void givenEmptyStreamThenEmptyStream() {
        List<String> emptyList = Collections.emptyList();
        EmployeeStringsSource source = emptyList::stream;
        assertEquals(0, new EmployeesStringParser(source).getEmployees().count());
    }

    @Test
    void givenStreamWithCeoThenCeoEntity() {
        List<String> emptyList = Collections.singletonList("123,Joe,Doe,60000,");
        EmployeeStringsSource source = emptyList::stream;
        List<EmployeeWithParentId> parsed = new EmployeesStringParser(source).getEmployees().toList();
        assertEquals(1, parsed.size());
        assertEquals(new EmployeeWithParentId(new Employee(123, "Doe", "Joe", 60000), null),
                parsed.getFirst());
    }

    @Test
    void givenStreamWithCorrectListThenCeoEntity() {
        EmployeeStringsSource source = () -> Stream.of("123,Joe,Doe,60000,",
                "124,Martin,Chekov,45000,123",
                "125,Bob,Ronstad,47000,123",
                "300,Alice,Hasacat,50000,124");
        List<EmployeeWithParentId> parsed = new EmployeesStringParser(source).getEmployees().toList();
        assertEquals(4, parsed.size());
        assertEquals(new EmployeeWithParentId(new Employee(123, "Doe", "Joe", 60000), null),
                parsed.getFirst());
        assertEquals(new EmployeeWithParentId(new Employee(124, "Chekov", "Martin", 45000), 123L),
                parsed.get(1));
        assertEquals(new EmployeeWithParentId(new Employee(125, "Ronstad", "Bob", 47000), 123L),
                parsed.get(2));
        assertEquals(new EmployeeWithParentId(new Employee(300, "Hasacat", "Alice", 50000), 124L),
                parsed.get(3));
    }

    @Test
    void givenStreamWithBrokenLineThenException() {
        List<String> emptyList = Collections.singletonList("123,Joe,Doe");
        EmployeeStringsSource source = emptyList::stream;
        try {
            new EmployeesStringParser(source).getEmployees().toList();
            fail();
        } catch (IncorrectFieldsCountException e) {
            assertTrue(e.getMessage().contains("contains different "));
        }
    }

    @Test
    void givenStreamWithNonNumberInNumberFieldThenException() {
        List<String> emptyList = Collections.singletonList("123,Joe,Doe,60K,");
        EmployeeStringsSource source = emptyList::stream;
        try {
            new EmployeesStringParser(source).getEmployees().toList();
            fail();
        } catch (CannotParseNumberException e) {
            assertTrue(e.getMessage().contains("Cannot parse number: "));
        }
    }
}