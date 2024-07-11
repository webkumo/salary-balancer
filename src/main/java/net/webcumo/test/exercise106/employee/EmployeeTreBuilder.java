package net.webcumo.test.exercise106.employee;

import net.webcumo.test.exercise106.tree.TreeBuilder;
import net.webcumo.test.exercise106.tree.TreeElementsSource;

public class EmployeeTreBuilder extends TreeBuilder<Employee> {
    public EmployeeTreBuilder(TreeElementsSource<Employee> source) {
        super(Employee.ROOT_NAME, source);
    }
}
