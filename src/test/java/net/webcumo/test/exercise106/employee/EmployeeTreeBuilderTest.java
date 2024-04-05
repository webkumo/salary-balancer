package net.webcumo.test.exercise106.employee;

import net.webcumo.test.exercise106.EmployeeTestsCasesBuilder;
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
                new EmployeeWithParentId(EmployeeTestsCasesBuilder.getEmployee(10), 11L));
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
                new EmployeeWithParentId(EmployeeTestsCasesBuilder.getEmployee(0), null),
                new EmployeeWithParentId(EmployeeTestsCasesBuilder.getEmployee(10), null));
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
                new EmployeeWithParentId(EmployeeTestsCasesBuilder.getEmployee(0), null),
                new EmployeeWithParentId(EmployeeTestsCasesBuilder.getEmployee(10), 0L),
                new EmployeeWithParentId(EmployeeTestsCasesBuilder.getEmployee(11), 10L),
                new EmployeeWithParentId(EmployeeTestsCasesBuilder.getEmployee(12), 8L)
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
                new EmployeeWithParentId(EmployeeTestsCasesBuilder.getEmployee(0), null),
                new EmployeeWithParentId(EmployeeTestsCasesBuilder.getEmployee(10), 0L),
                new EmployeeWithParentId(EmployeeTestsCasesBuilder.getEmployee(11), 10L),
                new EmployeeWithParentId(EmployeeTestsCasesBuilder.getEmployee(12), 10L)
                );
        EmployeesSource source = employees::stream;
        EmployeeTreeBuilder builder = new EmployeeTreeBuilder(source);
        EmployeeTreeElement ceo = builder.build();
        assertEquals(0, ceo.getEmployee().id());
        assertEquals(1, ceo.getSubordinates().size());
        EmployeeTreeElement manager = ceo.getSubordinates().getFirst();
        assertEquals(10, manager.getEmployee().id());
        assertEquals(2, manager.getSubordinates().size());
        assertEquals(2,
                manager.getSubordinates().stream()
                        .map(EmployeeTreeElement::getEmployee)
                        .filter(e -> e.id() == 11 || e.id() == 12)
                        .count());
    }

}