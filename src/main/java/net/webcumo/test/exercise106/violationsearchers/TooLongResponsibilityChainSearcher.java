package net.webcumo.test.exercise106.violationsearchers;

import net.webcumo.test.exercise106.employee.EmployeeTreeElement;

public class TooLongResponsibilityChainSearcher implements ViolationSearcher {

    static final String VIOLATION = "The employee %s has more than 4 (%s) managers before CEO.%n";

    @Override
    public void searchViolations(EmployeeTreeElement tree) {
        searchViolations(tree, 0);
    }

    private void searchViolations(EmployeeTreeElement employee, int level) {
        if (level > 5) {
            System.out.printf(VIOLATION, employee.getEmployee(), level - 1);
        }
        employee.getSubordinates().parallelStream()
                .forEach(e -> searchViolations(e, level + 1));
    }
}
