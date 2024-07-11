package net.webcumo.test.exercise106.tree;

import java.util.stream.Stream;

public interface TreeElementsSource<E extends TreeValue> {

    Stream<E> getValues();
}
