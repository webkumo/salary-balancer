package net.webcumo.test.exercise106.parser;

import java.util.stream.Stream;

public interface EmployeesSource {

    Stream<EmployeeWithParentId> getEmployees();
}
