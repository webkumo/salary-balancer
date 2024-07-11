package net.webcumo.test.exercise106.employee;

import net.webcumo.test.exercise106.tree.TreeValue;

public record Employee (long id, String lastName, String firstName, double salary, Long parentId)
        implements TreeValue {
    public static final String ROOT_NAME = "CEO";

    @Override
    public String toString() {
        return lastName + " " + firstName + " (" + id + ")";
    }

    @Override
    public Long getParentId() {
        return parentId;
    }

    @Override
    public long getId() {
        return id;
    }
}
