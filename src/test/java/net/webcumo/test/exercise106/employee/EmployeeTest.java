package net.webcumo.test.exercise106.employee;

import net.webcumo.test.exercise106.exceptions.employee.CannotCalculateAverageSalaryException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void givenEmployeeWithNoSubordinatesThenExceptionOnAverageCall() {
        Employee employee = new Employee(10 ,"Doe", "Joe", 2000);
        try {
            employee.getSubordinatesAverageSalary();
            fail();
        } catch (CannotCalculateAverageSalaryException e) {
            //ok
        }
    }

}