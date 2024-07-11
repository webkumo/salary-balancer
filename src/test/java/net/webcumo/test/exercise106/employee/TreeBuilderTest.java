package net.webcumo.test.exercise106.employee;

import net.webcumo.test.exercise106.EmployeeTestsCasesBuilder;
import net.webcumo.test.exercise106.exceptions.builder.NoRootFoundException;
import net.webcumo.test.exercise106.exceptions.builder.OrphansFoundException;
import net.webcumo.test.exercise106.exceptions.builder.TooManyRootsException;
import net.webcumo.test.exercise106.tree.TreeElementsSource;
import net.webcumo.test.exercise106.tree.TreeBuilder;
import net.webcumo.test.exercise106.tree.TreeElement;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreeBuilderTest {

    @Test
    void givenNoEmployeesThanException() {
        List<Employee> employees = Collections.emptyList();
        TreeElementsSource<Employee> source = employees::stream;
        TreeBuilder<Employee> builder = new EmployeeTreBuilder(source);
        assertThrows(NoRootFoundException.class, builder::build);
    }

    @Test
    void givenNoCeoThanException() {
        List<Employee> employees = Collections.singletonList(
                EmployeeTestsCasesBuilder.getEmployee(10, 11L));
        TreeElementsSource<Employee> source = employees::stream;
        TreeBuilder<Employee> builder = new EmployeeTreBuilder(source);
        assertThrows(NoRootFoundException.class, builder::build);
    }

    @Test
    void givenMoreThanOneCeoThanException() {
        List<Employee> employees = List.of(
                EmployeeTestsCasesBuilder.getEmployee(0, null),
                EmployeeTestsCasesBuilder.getEmployee(10, null));
        TreeElementsSource<Employee> source = employees::stream;
        TreeBuilder<Employee> builder = new EmployeeTreBuilder(source);
        assertThrows(TooManyRootsException.class, builder::build);
    }

    @Test
    void givenOrphansExistThanException() {
        List<Employee> employees = List.of(
                EmployeeTestsCasesBuilder.getEmployee(0, null),
                EmployeeTestsCasesBuilder.getEmployee(10, 0L),
                EmployeeTestsCasesBuilder.getEmployee(11, 10L),
                EmployeeTestsCasesBuilder.getEmployee(12, 8L)
                );
        TreeElementsSource<Employee> source = employees::stream;
        TreeBuilder<Employee> builder = new EmployeeTreBuilder(source);
        assertThrows(OrphansFoundException.class, builder::build);
    }

    @Test
    void givenCorrectConfigurationThanTreeIsBuilt() {
        List<Employee> employees = List.of(
                EmployeeTestsCasesBuilder.getEmployee(0, null),
                EmployeeTestsCasesBuilder.getEmployee(11, 10L),
                EmployeeTestsCasesBuilder.getEmployee(10, 0L),
                EmployeeTestsCasesBuilder.getEmployee(12, 10L)
                );
        TreeElementsSource<Employee> source = employees::stream;
        TreeBuilder<Employee> builder = new EmployeeTreBuilder(source);
        TreeElement<Employee> ceo = builder.build();
        assertEquals(0, ceo.getValue().id());
        assertEquals(1, ceo.getSubordinates().size());
        TreeElement<Employee> manager = ceo.getSubordinates().getFirst();
        assertEquals(10, manager.getValue().id());
        assertEquals(2, manager.getSubordinates().size());
        assertEquals(2,
                manager.getSubordinates().stream()
                        .map(TreeElement::getValue)
                        .filter(e -> e.id() == 11 || e.id() == 12)
                        .count());
    }

}