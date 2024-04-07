package net.webcumo.test.exercise106.employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeTreeElement {
    private final Employee employee;
    private final List<EmployeeTreeElement> subordinates = new ArrayList<>();

    public EmployeeTreeElement(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void addSubordinate(EmployeeTreeElement employee) {
        subordinates.add(employee);
    }

    public void addSubordinates(List<EmployeeTreeElement> subordinates) {
        subordinates.forEach(this::addSubordinate);
    }

    public boolean hasSubordinates() {
        return !subordinates.isEmpty();
    }

    public List<EmployeeTreeElement> getSubordinates() {
        return subordinates;
    }
}
