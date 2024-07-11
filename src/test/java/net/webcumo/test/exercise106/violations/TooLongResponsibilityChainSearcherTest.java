package net.webcumo.test.exercise106.violations;

import net.webcumo.test.exercise106.EmployeeTestsCasesBuilder;
import net.webcumo.test.exercise106.employee.Employee;
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
    private ViolationSearcher<Employee> searcher;


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
        assertTrue(errorMessages.contains(getViolationMessage(14, 5, 11L)));
        assertTrue(errorMessages.contains(getViolationMessage(15, 5, 11L)));
        assertTrue(errorMessages.contains(getViolationMessage(16, 6, 12L)));
        assertTrue(errorMessages.contains(getViolationMessage(17, 6, 12L)));
        assertEquals(4, errorMessages.split("\n").length);
    }

    private static String getViolationMessage(int id, int level, Long parentId) {
        return TooLongResponsibilityChainSearcher.VIOLATION
                .formatted(EmployeeTestsCasesBuilder.getEmployee(id, parentId), level);
    }

}