package net.webcumo.test.exercise106.violationsearchers;

import net.webcumo.test.exercise106.employee.Employee;

public class TooLongResponsibilityChainSearcher implements ViolationSearcher {

    static final String VIOLATION = "The employee %s %s (%s) has more than 4 managers before CEO.%n";

    @Override
    public void searchViolations(Employee tree) {
        searchViolations(tree, 0);
    }

    private void searchViolations(Employee employee, int level) {
        if (level > 5) {
            System.out.printf((VIOLATION), employee.getLastName(), employee.getFirstName(), employee.getId());
        }
        employee.getSubordinates().parallelStream()
                .forEach(e -> searchViolations(e, level + 1));
    }
}
