package net.webcumo.test.exercise106.violationsearchers;

import net.webcumo.test.exercise106.employee.Employee;

public class ManagersSalaryTooLowSearcher extends AbstractManagerViolationSearcher {
    static final String VIOLATION = "Manager %s has lower than 120%% of it's subordinates average.%n";

    @Override
    void checkViolations(Employee manager, double averageSubordinatesSalary) {
        if (manager.salary() < averageSubordinatesSalary * 1.2) {
            System.out.printf(VIOLATION, manager);
        }
    }

}
