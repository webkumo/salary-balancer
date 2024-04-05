package net.webcumo.test.exercise106.violationsearchers;

import net.webcumo.test.exercise106.employee.Employee;
import net.webcumo.test.exercise106.employee.EmployeeTreeElement;
import net.webcumo.test.exercise106.exceptions.employee.CannotCalculateAverageSalaryException;

public abstract class AbstractManagerViolationSearcher implements ViolationSearcher {
    double calculateSubordinatesAverageSalary(EmployeeTreeElement treeElement) {
        return treeElement.getSubordinates().stream()
                .map(EmployeeTreeElement::getEmployee)
                .mapToDouble(Employee::salary)
                .average().orElseThrow(() -> new CannotCalculateAverageSalaryException(treeElement.getEmployee()));
    }
}
