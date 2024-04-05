package net.webcumo.test.exercise106.employee;

public record Employee (long id, String lastName, String firstName, double salary) {
    @Override
    public String toString() {
        return lastName + " " + firstName + " (" + id + ")";
    }
}
