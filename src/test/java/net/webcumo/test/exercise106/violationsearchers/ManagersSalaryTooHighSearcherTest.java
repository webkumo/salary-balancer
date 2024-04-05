package net.webcumo.test.exercise106.violationsearchers;

import net.webcumo.test.exercise106.EmployeeTestsBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ManagersSalaryTooHighSearcherTest {
    private static final PrintStream DEFAULT = System.out;

    private ByteArrayOutputStream captor;
    private ViolationSearcher searcher;


    @BeforeEach
    public void setUp() {
        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
        searcher = new ManagersSalaryTooHighSearcher();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(DEFAULT);
    }

    @Test
    void givenNotAManagerThenNoMessageExpected() {
        searcher.searchViolations(EmployeeTestsBuilder.getNotManager());
        assertEquals(0, captor.size());
    }

    @Test
    void givenManagerWithNoViolationThenNoMessageExpected() {
        searcher.searchViolations(EmployeeTestsBuilder.getManagerWithoutViolations());
        assertEquals(0, captor.size());
    }

    @Test
    void givenManagerWithViolationsThenMessagesExpected() {
        searcher.searchViolations(EmployeeTestsBuilder.get3ManagersWithTooHighViolation());
        String errorMessages = captor.toString();
        assertTrue(errorMessages.contains(getViolationMessage(0)));
        assertTrue(errorMessages.contains(getViolationMessage(2)));
        assertTrue(errorMessages.contains(getViolationMessage(3)));
        assertEquals(3, errorMessages.split("\n").length);
    }

    private static String getViolationMessage(int id) {
        return ManagersSalaryTooHighSearcher.VIOLATION.formatted("Doe" + id, "Joe", id);
    }
}