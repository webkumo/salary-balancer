package net.webcumo.test.exercise106.violations;

import net.webcumo.test.exercise106.EmployeeTestsCasesBuilder;
import net.webcumo.test.exercise106.employee.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ManagersSalaryTooLowSearcherTest {
    private static final PrintStream DEFAULT = System.out;

    private ByteArrayOutputStream captor;
    private ViolationSearcher<Employee> searcher;


    @BeforeEach
    public void setUp() {
        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
        searcher = new ManagersSalaryTooLowSearcher();
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
    void givenManagerWithNoViolationThenNoMessageExpected() {
        searcher.searchViolations(EmployeeTestsCasesBuilder.getManagerWithoutViolations());
        assertEquals(0, captor.size());
    }

    @Test
    void givenManagerWithViolationsThenMessagesExpected() {
        searcher.searchViolations(EmployeeTestsCasesBuilder.get3ManagersWithTooLowViolation());
        String errorMessages = captor.toString();
        assertTrue(errorMessages.contains(getViolationMessage(0, null)));
        assertTrue(errorMessages.contains(getViolationMessage(2, 0L)));
        assertTrue(errorMessages.contains(getViolationMessage(4, 1L)));
        assertEquals(3, errorMessages.split("\n").length);
    }

    private static String getViolationMessage(int id, Long parentId) {
        return ManagersSalaryTooLowSearcher.VIOLATION
                .formatted(EmployeeTestsCasesBuilder.getEmployee(id, parentId));
    }

}