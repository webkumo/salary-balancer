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

    private final Map<Long, Employee> employees = new HashMap<>();
    private final Map<Long, List<Employee>> subordinatesCache = new HashMap<>();
    private Employee ceo;

    private final EmployeesSource source;

    public EmployeeTreeBuilder(EmployeesSource source) {
        this.source = source;
    }

    public Employee build() {
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
                    .map(Employee::toString)
                    .toList();
            throw new OrphansFoundException(orphans);
        }
    }

    private void addEmployee(EmployeeWithParentId employeeWithParentId) {
        if (employeeWithParentId.parentId() == null) {
            setCeo(employeeWithParentId.employee());
        } else {
            addSubordinate(employeeWithParentId.employee(), employeeWithParentId.parentId());
        }
        registerEmployee(employeeWithParentId.employee());
    }

    private void setCeo(Employee employee) {
        if (ceo == null) {
            ceo = employee;
        } else {
            throw new TooManyCeosException(ceo, employee);
        }
    }

    private void addSubordinate(Employee employee, Long parentId) {
        Employee head = employees.get(parentId);
        if (head != null) {
            head.addSubordinate(employee);
        } else {
            subordinatesCache.computeIfAbsent(parentId, (id) -> new ArrayList<>())
                    .add(employee);
        }

        List<Employee> subordinates = subordinatesCache.get(employee.getId());
        if (subordinates != null) {
            employee.addSubordinates(subordinates);
            subordinatesCache.remove(employee.getId());
        }
    }

    private void registerEmployee(Employee employee) {
        Employee alreadyRegistered = employees.putIfAbsent(employee.getId(), employee);
        if (alreadyRegistered != null) {
            throw new EmployeeIdAlreadyUsedException(employee, alreadyRegistered);
        }
    }
}
