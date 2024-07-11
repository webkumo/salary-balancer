package net.webcumo.test.exercise106.violations;

import net.webcumo.test.exercise106.employee.Employee;

public class ManagersSalaryTooHighSearcher extends AbstractManagerViolationSearcher {
    private static final double MAXIMUM_COEFFICIENT = 1.5;
    static final String VIOLATION = "Manager %s has more than 150%% of it's subordinates average.%n";

    @Override
    void checkViolations(Employee manager, double averageSubordinatesSalary) {
        if (manager.salary() > getMaximumManagerSalary(averageSubordinatesSalary)) {
            System.out.printf(VIOLATION, manager);
        }
    }

    private static double getMaximumManagerSalary(double averageSubordinatesSalary) {
        return averageSubordinatesSalary * MAXIMUM_COEFFICIENT;
    }
}
