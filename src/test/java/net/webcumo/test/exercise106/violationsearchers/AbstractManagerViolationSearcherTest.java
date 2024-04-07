package net.webcumo.test.exercise106.violationsearchers;

import net.webcumo.test.exercise106.EmployeeTestsCasesBuilder;
import net.webcumo.test.exercise106.exceptions.employee.CannotCalculateAverageSalaryException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractManagerViolationSearcherTest {

    @Test
    void givenEmployeeWithNoSubordinatesThenExceptionOnAverageCall() {
        AbstractManagerViolationSearcher searcher = new ManagersSalaryTooHighSearcher();
        try {
            searcher.calculateSubordinatesAverageSalary(EmployeeTestsCasesBuilder.getNotManager());
            fail();
        } catch (CannotCalculateAverageSalaryException e) {
            //ok
        }
    }

}