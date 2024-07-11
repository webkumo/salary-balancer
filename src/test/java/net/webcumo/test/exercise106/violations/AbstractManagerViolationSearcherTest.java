package net.webcumo.test.exercise106.violations;

import net.webcumo.test.exercise106.EmployeeTestsCasesBuilder;
import net.webcumo.test.exercise106.employee.Employee;
import net.webcumo.test.exercise106.exceptions.employee.CannotCalculateAverageSalaryException;
import net.webcumo.test.exercise106.tree.TreeElement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractManagerViolationSearcherTest {

    @Test
    void givenEmployeeWithNoSubordinatesThenExceptionOnAverageCall() {
        AbstractManagerViolationSearcher searcher = new ManagersSalaryTooHighSearcher();
        TreeElement<Employee> notManager = EmployeeTestsCasesBuilder.getNotManager();
        assertThrows(CannotCalculateAverageSalaryException.class,
                () ->searcher.calculateSubordinatesAverageSalary(notManager));
    }

}