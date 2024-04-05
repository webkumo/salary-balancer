package net.webcumo.test.exercise106.employee;

import net.webcumo.test.exercise106.exceptions.builder.EmployeeIdAlreadyUsedException;
import net.webcumo.test.exercise106.exceptions.builder.NoCeoFoundException;
import net.webcumo.test.exercise106.exceptions.builder.OrphansFoundException;
import net.webcumo.test.exercise106.exceptions.builder.TooManyCeosException;
import net.webcumo.test.exercise106.parser.EmployeesSource;
import net.webcumo.test.exercise106.parser.EmployeeWithParentId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeTreeBuilder {

    private final Map<Long, EmployeeTreeElement> employees = new HashMap<>();
    private final Map<Long, List<EmployeeTreeElement>> subordinatesCache = new HashMap<>();
    private EmployeeTreeElement ceo;

    private final EmployeesSource source;

    public EmployeeTreeBuilder(EmployeesSource source) {
        this.source = source;
    }

    public EmployeeTreeElement build() {
        source.getEmployees().forEach(this::addEmployee);
        validateCeo();
        validateNoOrphans();
        return ceo;
    }

    private void validateCeo() {
        if (ceo == null) {
            throw new NoCeoFoundException();
        }
    }

    private void validateNoOrphans() {
        if (!subordinatesCache.isEmpty()) {
            List<String> orphans = subordinatesCache.values().stream()
                    .flatMap(List::stream)
                    .map(EmployeeTreeElement::getEmployee)
                    .map(Employee::toString)
                    .toList();
            throw new OrphansFoundException(orphans);
        }
    }

    private void addEmployee(EmployeeWithParentId employeeWithParentId) {
        EmployeeTreeElement treeElement = new EmployeeTreeElement(employeeWithParentId.employee());
        if (employeeWithParentId.parentId() == null) {
            setCeo(treeElement);
        } else {
            addSubordinate(treeElement, employeeWithParentId.parentId());
        }
        registerEmployee(treeElement);
    }

    private void setCeo(EmployeeTreeElement employee) {
        if (ceo == null) {
            ceo = employee;
        } else {
            throw new TooManyCeosException(ceo.getEmployee(), employee.getEmployee());
        }
    }

    private void addSubordinate(EmployeeTreeElement employee, Long parentId) {
        EmployeeTreeElement head = employees.get(parentId);
        if (head != null) {
            head.addSubordinate(employee);
        } else {
            subordinatesCache.computeIfAbsent(parentId, (id) -> new ArrayList<>())
                    .add(employee);
        }

        List<EmployeeTreeElement> subordinates = subordinatesCache.get(employee.getEmployee().id());
        if (subordinates != null) {
            employee.addSubordinates(subordinates);
            subordinatesCache.remove(employee.getEmployee().id());
        }
    }

    private void registerEmployee(EmployeeTreeElement employee) {
        EmployeeTreeElement alreadyRegistered = employees.putIfAbsent(employee.getEmployee().id(), employee);
        if (alreadyRegistered != null) {
            throw new EmployeeIdAlreadyUsedException(employee.getEmployee(), alreadyRegistered.getEmployee());
        }
    }
}
