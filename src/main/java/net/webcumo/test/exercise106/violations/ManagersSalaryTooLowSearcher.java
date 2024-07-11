package net.webcumo.test.exercise106.violations;

import net.webcumo.test.exercise106.employee.Employee;

public class ManagersSalaryTooLowSearcher extends AbstractManagerViolationSearcher {
    static final String VIOLATION = "Manager %s has lower than 120%% of it's subordinates average.%n";
    private static final double MINIMAL_MANAGER_COEFFICIENT = 1.2;

    @Override
    void checkViolations(Employee manager, double averageSubordinatesSalary) {
        if (manager.salary() < getMinimalManagerSalary(averageSubordinatesSalary)) {
            System.out.printf(VIOLATION, manager);
        }
    }

    private static double getMinimalManagerSalary(double averageSubordinatesSalary) {
        return averageSubordinatesSalary * MINIMAL_MANAGER_COEFFICIENT;
    }

}
