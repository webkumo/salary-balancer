package net.webcumo.test.exercise106.employee;

import net.webcumo.test.exercise106.exceptions.employee.CannotCalculateAverageSalaryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employee {

    private Employee parent;
    private final Long id;
    private final String lastName;
    private final String firstName;
    private final double salary;
    private final List<Employee> subordinates = new ArrayList<>();

    public Employee(long id, String lastName, String firstName, double salary) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public double getSalary() {
        return salary;
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public double getSubordinatesAverageSalary() {
        return subordinates.stream()
                .mapToDouble(Employee::getSalary)
                .average().orElseThrow(() -> new CannotCalculateAverageSalaryException(this));
    }

    public boolean isNotManager() {
        return subordinates.isEmpty();
    }

    public boolean isManager() {
        return !isNotManager();
    }

    public void setParent(Employee parent) {
        this.parent = parent;
    }

    public void addSubordinate(Employee employee) {
        subordinates.add(employee);
        employee.setParent(this);
    }

    public void addSubordinates(List<Employee> subordinates) {
        this.subordinates.addAll(subordinates);
        this.subordinates.forEach(e -> e.setParent(this));
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " (" + id + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Double.compare(salary, employee.salary) == 0
                && Objects.equals(parent, employee.parent)
                && Objects.equals(id, employee.id)
                && Objects.equals(lastName, employee.lastName)
                && Objects.equals(firstName, employee.firstName)
                && Objects.equals(subordinates, employee.subordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, id, lastName, firstName, salary, subordinates);
    }
}
