package net.webcumo.test.exercise106.tree;

import net.webcumo.test.exercise106.exceptions.builder.ElementIdAlreadyUsedException;
import net.webcumo.test.exercise106.exceptions.builder.NoRootFoundException;
import net.webcumo.test.exercise106.exceptions.builder.OrphansFoundException;
import net.webcumo.test.exercise106.exceptions.builder.TooManyRootsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TreeBuilder<E extends TreeValue> {

    private final Map<Long, TreeElement<E>> treeElements = new HashMap<>();
    private final Map<Long, List<TreeElement<E>>> childrenCache = new HashMap<>();

    private final String rootElementName;

    private TreeElement<E> rootElement;

    private final TreeElementsSource<E> source;

    public TreeBuilder(String rootElementName, TreeElementsSource<E> source) {
        this.rootElementName = rootElementName;
        this.source = source;
    }

    public TreeElement<E> build() {
        source.getValues().forEach(this::addElement);
        validateRootExists();
        validateNoOrphans();
        return rootElement;
    }

    private void validateRootExists() {
        if (rootElement == null) {
            throw new NoRootFoundException(rootElementName);
        }
    }

    private void validateNoOrphans() {
        if (!childrenCache.isEmpty()) {
            List<String> orphans = childrenCache.values().stream()
                    .flatMap(List::stream)
                    .map(TreeElement::getValue)
                    .map(E::toString)
                    .toList();
            throw new OrphansFoundException(orphans);
        }
    }

    private void addElement(E elementValue) {
        TreeElement<E> treeElement = new TreeElement<>(elementValue);
        if (elementValue.getParentId() == null) {
            setRootElement(treeElement);
        } else {
            addChild(treeElement);
        }
        registerChildren(treeElement);
    }

    private void setRootElement(TreeElement<E> root) {
        if (rootElement == null) {
            rootElement = root;
        } else {
            throw new TooManyRootsException(rootElementName,
                    rootElement.getValue(),
                    root.getValue());
        }
    }

    private void addChild(TreeElement<E> employee) {
        TreeElement<E> head = treeElements.get(employee.getValue().getParentId());
        if (head != null) {
            head.addSubordinate(employee);
        } else {
            childrenCache.computeIfAbsent(employee.getValue().getParentId(),
                            (id) -> new ArrayList<>())
                    .add(employee);
        }

        List<TreeElement<E>> subordinates = childrenCache.get(employee.getValue().getId());
        if (subordinates != null) {
            employee.addSubordinates(subordinates);
            childrenCache.remove(employee.getValue().getId());
        }
    }

    private void registerChildren(TreeElement<E> employee) {
        TreeElement<E> alreadyRegistered = treeElements.putIfAbsent(employee.getValue().getId(), employee);
        if (alreadyRegistered != null) {
            throw new ElementIdAlreadyUsedException(employee.getValue(), alreadyRegistered.getValue());
        }
    }
}
