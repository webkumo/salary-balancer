package net.webcumo.test.exercise106.parser;

import net.webcumo.test.exercise106.employee.Employee;

import java.util.Objects;

public record EmployeeWithParentId(Employee employee, Long parentId) {
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmployeeWithParentId that = (EmployeeWithParentId) o;
        return Objects.equals(employee, that.employee) && Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, parentId);
    }
}
