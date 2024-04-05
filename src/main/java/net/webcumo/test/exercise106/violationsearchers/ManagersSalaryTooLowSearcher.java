package net.webcumo.test.exercise106.violationsearchers;

import net.webcumo.test.exercise106.employee.EmployeeTreeElement;

public class ManagersSalaryTooLowSearcher extends AbstractManagerViolationSearcher {
    static final String VIOLATION = "Manager %s has lower than 120%% of it's subordinates average.%n";

    @Override
    public void searchViolations(EmployeeTreeElement ceo) {
        if (ceo.hasSubordinates()) {
            searchViolationsOnManager(ceo);
        }
    }

    public void searchViolationsOnManager(EmployeeTreeElement manager) {
        if (manager.getEmployee().salary() < calculateSubordinatesAverageSalary(manager) * 1.2) {
            System.out.printf(VIOLATION, manager.getEmployee());
        }
        manager.getSubordinates().parallelStream()
                .filter(EmployeeTreeElement::hasSubordinates)
                .forEach(this::searchViolationsOnManager);
    }

}
