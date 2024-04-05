package net.webcumo.test.exercise106.violationsearchers;

import net.webcumo.test.exercise106.employee.Employee;
import net.webcumo.test.exercise106.employee.EmployeeTreeElement;
import net.webcumo.test.exercise106.exceptions.employee.CannotCalculateAverageSalaryException;

public abstract class AbstractManagerViolationSearcher implements ViolationSearcher {
    @Override
    final public void searchViolations(EmployeeTreeElement ceo) {
        if (ceo.hasSubordinates()) {
            searchViolationsOnManager(ceo);
        }
    }

    abstract void checkViolations(Employee manager, double averageSubordinatesSalary);

    double calculateSubordinatesAverageSalary(EmployeeTreeElement treeElement) {
        return treeElement.getSubordinates().stream()
                .map(EmployeeTreeElement::getEmployee)
                .mapToDouble(Employee::salary)
                .average().orElseThrow(() -> new CannotCalculateAverageSalaryException(treeElement.getEmployee()));
    }

    private void searchViolationsOnManager(EmployeeTreeElement manager) {
        checkViolations(manager.getEmployee(), calculateSubordinatesAverageSalary(manager));
        manager.getSubordinates().parallelStream()
                .filter(EmployeeTreeElement::hasSubordinates)
                .forEach(this::searchViolationsOnManager);
    }
}
