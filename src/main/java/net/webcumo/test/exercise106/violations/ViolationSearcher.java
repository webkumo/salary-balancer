package net.webcumo.test.exercise106.violations;

import net.webcumo.test.exercise106.tree.TreeElement;
import net.webcumo.test.exercise106.tree.TreeValue;

public interface ViolationSearcher<E extends TreeValue> {
    void searchViolations(TreeElement<E> tree);
}
