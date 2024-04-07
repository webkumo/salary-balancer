package net.webcumo.test.exercise106.violationsearchers;

import net.webcumo.test.exercise106.EmployeeTestsCasesBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TooLongResponsibilityChainSearcherTest {
    private static final PrintStream DEFAULT = System.out;

    private ByteArrayOutputStream captor;
    private ViolationSearcher searcher;


    @BeforeEach
    public void setUp() {
        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
        searcher = new TooLongResponsibilityChainSearcher();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(DEFAULT);
    }

    @Test
    void givenEmployeeWithNoSubordinatesThenOk() {
        searcher.searchViolations(EmployeeTestsCasesBuilder.getNotManager());
        Assertions.assertEquals(0, captor.size());
    }

    @Test
    void givenChainOf3ThenNoMessageExpected() {
        searcher.searchViolations(EmployeeTestsCasesBuilder.getManagerWithoutViolations());
        assertEquals(0, captor.size());
    }

    @Test
    void givenChainOf6ThenMessagesExpected() {
        searcher.searchViolations(EmployeeTestsCasesBuilder.getTooBigChainViolation());
        String errorMessages = captor.toString();
        assertTrue(errorMessages.contains(getViolationMessage(15, 5)));
        assertTrue(errorMessages.contains(getViolationMessage(16, 6)));
        assertTrue(errorMessages.contains(getViolationMessage(14, 5)));
        assertTrue(errorMessages.contains(getViolationMessage(17, 6)));
        assertEquals(4, errorMessages.split("\n").length);
    }

    private static String getViolationMessage(int id, int count) {
        return TooLongResponsibilityChainSearcher.VIOLATION
                .formatted(EmployeeTestsCasesBuilder.getEmployeeElement(id).getEmployee(), count);
    }

}