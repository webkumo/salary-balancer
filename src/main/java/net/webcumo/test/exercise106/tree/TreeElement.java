package net.webcumo.test.exercise106.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeElement<E extends TreeValue> {
    private final E value;
    private final List<TreeElement<E>> subordinates = new ArrayList<>();

    public TreeElement(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public void addSubordinate(TreeElement<E> employee) {
        subordinates.add(employee);
    }

    public void addSubordinates(List<TreeElement<E>> subordinates) {
        subordinates.forEach(this::addSubordinate);
    }

    public boolean hasSubordinates() {
        return !subordinates.isEmpty();
    }

    public List<TreeElement<E>> getSubordinates() {
        return subordinates;
    }
}
