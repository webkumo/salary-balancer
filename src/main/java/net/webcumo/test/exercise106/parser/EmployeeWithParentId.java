package net.webcumo.test.exercise106.parser;

import net.webcumo.test.exercise106.employee.Employee;

public record EmployeeWithParentId(Employee employee, Long parentId) {
}
