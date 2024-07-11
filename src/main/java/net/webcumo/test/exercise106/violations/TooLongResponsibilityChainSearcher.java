package net.webcumo.test.exercise106.violations;

import net.webcumo.test.exercise106.employee.Employee;
import net.webcumo.test.exercise106.tree.TreeElement;

public class TooLongResponsibilityChainSearcher implements ViolationSearcher<Employee> {

    static final String VIOLATION = "The employee %s has more than 4 (%s) managers before CEO.%n";
    private static final int LEVEL_WITH_MAX_MANAGERS_BEFORE_CEO = 5;

    @Override
    public void searchViolations(TreeElement<Employee> tree) {
        searchViolations(tree, 0);
    }

    private void searchViolations(TreeElement<Employee> employee, int level) {
        if (level > LEVEL_WITH_MAX_MANAGERS_BEFORE_CEO) {
            System.out.printf(VIOLATION, employee.getValue(), level - 1);
        }
        employee.getSubordinates().parallelStream()
                .forEach(e -> searchViolations(e, level + 1));
    }
}
