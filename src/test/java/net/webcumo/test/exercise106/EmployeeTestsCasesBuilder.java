package net.webcumo.test.exercise106;

import net.webcumo.test.exercise106.employee.Employee;
import net.webcumo.test.exercise106.tree.TreeElement;

import java.util.List;

public class EmployeeTestsCasesBuilder {
    public static TreeElement<Employee> getNotManager() {
        return getEmployeeElement(0, 1000.0, null);
    }

    public static TreeElement<Employee> getManagerWithoutViolations() {
        TreeElement<Employee> ceo = getEmployeeElement(0, 6000.0, null);
        TreeElement<Employee> manager1Level1 = getEmployeeElement(1, 5000.0, 0L);
        TreeElement<Employee> manager2level1 = getEmployeeElement(2, 4000.0, 0L);
        ceo.addSubordinates(List.of(manager1Level1, manager2level1));

        TreeElement<Employee> manager3level2 = getEmployeeElement(3, 4000.0, 1L);
        TreeElement<Employee> manager4level2 = getEmployeeElement(4, 3500.0, 1L);
        manager1Level1.addSubordinates(List.of(manager3level2, manager4level2));
        manager3level2.addSubordinate(getEmployeeElement(5, 3000.0, 3L));
        manager3level2.addSubordinate(getEmployeeElement(6, 2500.0, 3L));
        manager3level2.addSubordinate(getEmployeeElement(7, 3500.0, 3L));
        manager4level2.addSubordinate(getEmployeeElement(8, 2500.0, 4L));
        manager4level2.addSubordinate(getEmployeeElement(9, 3000.0, 4L));

        manager2level1.addSubordinate(getEmployeeElement(10, 3100.0, 2L));
        manager2level1.addSubordinate(getEmployeeElement(11, 3000.0, 2L));
        manager2level1.addSubordinate(getEmployeeElement(12, 5000.0, 2L));
        manager2level1.addSubordinate(getEmployeeElement(13, 1000.0, 2L));
        manager2level1.addSubordinate(getEmployeeElement(14, 3000.0, 2L));
        manager2level1.addSubordinate(getEmployeeElement(15, 3000.0, 2L));
        return ceo;
    }

    public static TreeElement<Employee> get3ManagersWithTooHighViolation() {
        TreeElement<Employee> ceo = getEmployeeElement(0, 600000.0, null);
        TreeElement<Employee> manager1Level1 = getEmployeeElement(1, 5000.0, 0L);
        TreeElement<Employee> manager2level1 = getEmployeeElement(2, 40000.0, 0L);
        ceo.addSubordinates(List.of(manager1Level1, manager2level1));

        TreeElement<Employee> manager3level2 = getEmployeeElement(3, 40000.0, 1L);
        TreeElement<Employee> manager4level2 = getEmployeeElement(4, 3500.0, 1L);
        manager1Level1.addSubordinates(List.of(manager3level2, manager4level2));
        manager3level2.addSubordinate(getEmployeeElement(5, 3000.0, 3L));
        manager3level2.addSubordinate(getEmployeeElement(6, 2500.0, 3L));
        manager3level2.addSubordinate(getEmployeeElement(7, 3500.0, 3L));
        manager4level2.addSubordinate(getEmployeeElement(8, 2500.0, 4L));
        manager4level2.addSubordinate(getEmployeeElement(9, 3000.0, 4L));

        manager2level1.addSubordinate(getEmployeeElement(10, 3100.0, 2L));
        manager2level1.addSubordinate(getEmployeeElement(11, 3000.0, 2L));
        manager2level1.addSubordinate(getEmployeeElement(12, 5000.0, 2L));
        manager2level1.addSubordinate(getEmployeeElement(13, 1000.0, 2L));
        manager2level1.addSubordinate(getEmployeeElement(14, 3000.0, 2L));
        manager2level1.addSubordinate(getEmployeeElement(15, 3000.0, 2L));
        return ceo;
    }

    public static TreeElement<Employee> get3ManagersWithTooLowViolation() {
        TreeElement<Employee> ceo = getEmployeeElement(0, 1.0, null);
        TreeElement<Employee> manager1Level1 = getEmployeeElement(1, 5000.0, 0L);
        TreeElement<Employee> manager2level1 = getEmployeeElement(2, 3000.0, 0L);
        ceo.addSubordinates(List.of(manager1Level1, manager2level1));

        TreeElement<Employee> manager3level2 = getEmployeeElement(3, 4000.0, 1L);
        TreeElement<Employee> manager4level2 = getEmployeeElement(4, 3000.0, 1L);
        manager1Level1.addSubordinates(List.of(manager3level2, manager4level2));
        manager3level2.addSubordinate(getEmployeeElement(5, 3000.0, 3L));
        manager3level2.addSubordinate(getEmployeeElement(6, 2500.0, 3L));
        manager3level2.addSubordinate(getEmployeeElement(7, 3500.0, 3L));
        manager4level2.addSubordinate(getEmployeeElement(8, 2500.0, 2L));
        manager4level2.addSubordinate(getEmployeeElement(9, 3000.0, 2L));

        manager2level1.addSubordinate(getEmployeeElement(10, 3100.0, 2L));
        manager2level1.addSubordinate(getEmployeeElement(11, 3000.0, 2L));
        manager2level1.addSubordinate(getEmployeeElement(12, 5000.0, 2L));
        manager2level1.addSubordinate(getEmployeeElement(13, 1000.0, 2L));
        manager2level1.addSubordinate(getEmployeeElement(14, 3000.0, 2L));
        manager2level1.addSubordinate(getEmployeeElement(15, 3000.0, 2L));
        return ceo;
    }

    public static TreeElement<Employee> getTooBigChainViolation() {
        TreeElement<Employee> ceo = getEmployeeElement(0, null);
        TreeElement<Employee> manager1Level1 = getEmployeeElement(1, 0L);
        TreeElement<Employee> manager2level1 = getEmployeeElement(2, 0L);
        ceo.addSubordinates(List.of(manager1Level1, manager2level1));

        TreeElement<Employee> manager3level2 = getEmployeeElement(3, 1L);
        TreeElement<Employee> manager4level2 = getEmployeeElement(4, 1L);
        manager1Level1.addSubordinates(List.of(manager3level2, manager4level2));
        TreeElement<Employee> manager5level3 = getEmployeeElement(5, 3L);
        manager3level2.addSubordinate(manager5level3);
        manager3level2.addSubordinate(getEmployeeElement(6, 3L));
        manager3level2.addSubordinate(getEmployeeElement(7, 3L));
        TreeElement<Employee> manager6Level3 = getEmployeeElement(8, 4L);
        manager4level2.addSubordinate(manager6Level3);

        TreeElement<Employee> manager7Level4 = getEmployeeElement(9, 5L);
        TreeElement<Employee> manager8Level4 = getEmployeeElement(10, 5L);
        manager5level3.addSubordinates(List.of(manager7Level4, manager8Level4));
        TreeElement<Employee> manager9Level4 = getEmployeeElement(11, 6L);
        manager6Level3.addSubordinate(manager9Level4);
        TreeElement<Employee> manager10Level5 = getEmployeeElement(12, 7L);
        manager7Level4.addSubordinate(manager10Level5);
        TreeElement<Employee> manager11Level5 = getEmployeeElement(13, 9L);
        manager9Level4.addSubordinate(manager11Level5);
        TreeElement<Employee> manager12Level6 = getEmployeeElement(14, 11L);
        TreeElement<Employee> manager13Level6 = getEmployeeElement(15, 11L);
        manager11Level5.addSubordinates(List.of(manager12Level6, manager13Level6));

        manager12Level6.addSubordinates(List.of(getEmployeeElement(16, 12L),
                getEmployeeElement(17, 12L)));

        return ceo;
    }

    public static Employee getEmployee(int id, Long parentId) {
        return getEmployee(id, 0, parentId);
    }

    public static TreeElement<Employee> getEmployeeElement(int id, Long parentId) {
        return getEmployeeElement(id, 0, parentId);
    }

    private static TreeElement<Employee> getEmployeeElement(int id, double salary, Long parentId) {
        return new TreeElement<>(getEmployee(id, salary, parentId));
    }

    private static Employee getEmployee(int id, double salary, Long parentId) {
        return new Employee(id, "Doe" + id, "Joe", salary, parentId);
    }
}
