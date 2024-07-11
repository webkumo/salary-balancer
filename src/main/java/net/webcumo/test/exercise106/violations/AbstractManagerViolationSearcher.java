package net.webcumo.test.exercise106.violations;

import net.webcumo.test.exercise106.employee.Employee;
import net.webcumo.test.exercise106.tree.TreeElement;
import net.webcumo.test.exercise106.exceptions.employee.CannotCalculateAverageSalaryException;

public abstract class AbstractManagerViolationSearcher implements ViolationSearcher<Employee> {
    @Override
    final public void searchViolations(TreeElement<Employee> ceo) {
        if (ceo.hasSubordinates()) {
            searchViolationsOnManager(ceo);
        }
    }

    abstract void checkViolations(Employee manager, double averageSubordinatesSalary);

    double calculateSubordinatesAverageSalary(TreeElement<Employee> treeElement) {
        return treeElement.getSubordinates().stream()
                .map(TreeElement::getValue)
                .mapToDouble(Employee::salary)
                .average().orElseThrow(() -> new CannotCalculateAverageSalaryException(treeElement.getValue()));
    }

    private void searchViolationsOnManager(TreeElement<Employee> manager) {
        checkViolations(manager.getValue(), calculateSubordinatesAverageSalary(manager));
        manager.getSubordinates().parallelStream()
                .filter(TreeElement::hasSubordinates)
                .forEach(this::searchViolationsOnManager);
    }
}
