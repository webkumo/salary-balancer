package net.webcumo.test.exercise106.employee;

import net.webcumo.test.exercise106.EmployeeTestsBuilder;
import net.webcumo.test.exercise106.exceptions.builder.NoCeoFoundException;
import net.webcumo.test.exercise106.exceptions.builder.OrphansFoundException;
import net.webcumo.test.exercise106.exceptions.builder.TooManyCeosException;
import net.webcumo.test.exercise106.parser.EmployeeWithParentId;
import net.webcumo.test.exercise106.parser.EmployeesSource;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTreeBuilderTest {

    @Test
    void givenNoEmployeesThanException() {
        List<EmployeeWithParentId> employees = Collections.emptyList();
        EmployeesSource source = employees::stream;
        EmployeeTreeBuilder builder = new EmployeeTreeBuilder(source);
        try {
            builder.build();
            fail();
        } catch (NoCeoFoundException e) {
            //correct way
        }
    }

    @Test
    void givenNoCeoThanException() {
        List<EmployeeWithParentId> employees = Collections.singletonList(
                new EmployeeWithParentId(EmployeeTestsBuilder.getEmployee(10), 11L));
        EmployeesSource source = employees::stream;
        EmployeeTreeBuilder builder = new EmployeeTreeBuilder(source);
        try {
            builder.build();
            fail();
        } catch (NoCeoFoundException e) {
            //correct way
        }
    }

    @Test
    void givenMoreThanOneCeoThanException() {
        List<EmployeeWithParentId> employees = List.of(
                new EmployeeWithParentId(EmployeeTestsBuilder.getEmployee(0), null),
                new EmployeeWithParentId(EmployeeTestsBuilder.getEmployee(10), null));
        EmployeesSource source = employees::stream;
        EmployeeTreeBuilder builder = new EmployeeTreeBuilder(source);
        try {
            builder.build();
            fail();
        } catch (TooManyCeosException e) {
            //correct way
        }
    }

    @Test
    void givenOrphansExistThanException() {
        List<EmployeeWithParentId> employees = List.of(
                new EmployeeWithParentId(EmployeeTestsBuilder.getEmployee(0), null),
                new EmployeeWithParentId(EmployeeTestsBuilder.getEmployee(10), 0L),
                new EmployeeWithParentId(EmployeeTestsBuilder.getEmployee(11), 10L),
                new EmployeeWithParentId(EmployeeTestsBuilder.getEmployee(12), 8L)
                );
        EmployeesSource source = employees::stream;
        EmployeeTreeBuilder builder = new EmployeeTreeBuilder(source);
        try {
            builder.build();
            fail();
        } catch (OrphansFoundException e) {
            //correct way
        }
    }

    @Test
    void givenCorrectConfigurationThanTreeIsBuilt() {
        List<EmployeeWithParentId> employees = List.of(
                new EmployeeWithParentId(EmployeeTestsBuilder.getEmployee(0), null),
                new EmployeeWithParentId(EmployeeTestsBuilder.getEmployee(10), 0L),
                new EmployeeWithParentId(EmployeeTestsBuilder.getEmployee(11), 10L),
                new EmployeeWithParentId(EmployeeTestsBuilder.getEmployee(12), 10L)
                );
        EmployeesSource source = employees::stream;
        EmployeeTreeBuilder builder = new EmployeeTreeBuilder(source);
        Employee ceo = builder.build();
        assertEquals(0, ceo.getId());
        assertEquals(1, ceo.getSubordinates().size());
        Employee manager = ceo.getSubordinates().getFirst();
        assertEquals(10, manager.getId());
        assertEquals(2, manager.getSubordinates().size());
        assertEquals(2,
                manager.getSubordinates().stream().filter(e -> e.getId() == 11 || e.getId() == 12).count());
    }

}