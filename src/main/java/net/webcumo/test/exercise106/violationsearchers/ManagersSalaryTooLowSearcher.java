package net.webcumo.test.exercise106.violationsearchers;

import net.webcumo.test.exercise106.employee.Employee;

public class ManagersSalaryTooLowSearcher implements ViolationSearcher {
    static final String VIOLATION = "Manager %s %s (%s) has lower than 120%% of it's subordinates average.%n";

    @Override
    public void searchViolations(Employee ceo) {
        if (ceo.isNotManager()) {
            return;
        }
        searchViolationsOnManager(ceo);
    }

    public void searchViolationsOnManager(Employee manager) {
        if (manager.getSalary() < manager.getSubordinatesAverageSalary() * 1.2) {
            System.out.printf(VIOLATION, manager.getLastName(), manager.getFirstName(), manager.getId());
        }
        manager.getSubordinates().parallelStream()
                .filter(Employee::isManager)
                .forEach(this::searchViolationsOnManager);
    }

}
