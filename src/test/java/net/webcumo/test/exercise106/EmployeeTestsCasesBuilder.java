package net.webcumo.test.exercise106;

import net.webcumo.test.exercise106.employee.Employee;
import net.webcumo.test.exercise106.employee.EmployeeTreeElement;

import java.util.List;

public class EmployeeTestsCasesBuilder {
    public static EmployeeTreeElement getNotManager() {
        return getEmployeeElement(0, 1000.0);
    }

    public static EmployeeTreeElement getManagerWithoutViolations() {
        EmployeeTreeElement ceo = getEmployeeElement(0, 6000.0);
        EmployeeTreeElement manager1Level1 = getEmployeeElement(1, 5000.0);
        EmployeeTreeElement manager2level1 = getEmployeeElement(2, 4000.0);
        ceo.addSubordinates(List.of(manager1Level1, manager2level1));

        EmployeeTreeElement manager3level2 = getEmployeeElement(3, 4000.0);
        EmployeeTreeElement manager4level2 = getEmployeeElement(4, 3500.0);
        manager1Level1.addSubordinates(List.of(manager3level2, manager4level2));
        manager3level2.addSubordinate(getEmployeeElement(5, 3000.0));
        manager3level2.addSubordinate(getEmployeeElement(6, 2500.0));
        manager3level2.addSubordinate(getEmployeeElement(7, 3500.0));
        manager4level2.addSubordinate(getEmployeeElement(8, 2500.0));
        manager4level2.addSubordinate(getEmployeeElement(9, 3000.0));

        manager2level1.addSubordinate(getEmployeeElement(10, 3100.0));
        manager2level1.addSubordinate(getEmployeeElement(11, 3000.0));
        manager2level1.addSubordinate(getEmployeeElement(12, 5000.0));
        manager2level1.addSubordinate(getEmployeeElement(13, 1000.0));
        manager2level1.addSubordinate(getEmployeeElement(14, 3000.0));
        manager2level1.addSubordinate(getEmployeeElement(15, 3000.0));
        return ceo;
    }

    public static EmployeeTreeElement get3ManagersWithTooHighViolation() {
        EmployeeTreeElement ceo = getEmployeeElement(0, 600000.0);
        EmployeeTreeElement manager1Level1 = getEmployeeElement(1, 5000.0);
        EmployeeTreeElement manager2level1 = getEmployeeElement(2, 40000.0);
        ceo.addSubordinates(List.of(manager1Level1, manager2level1));

        EmployeeTreeElement manager3level2 = getEmployeeElement(3, 40000.0);
        EmployeeTreeElement manager4level2 = getEmployeeElement(4, 3500.0);
        manager1Level1.addSubordinates(List.of(manager3level2, manager4level2));
        manager3level2.addSubordinate(getEmployeeElement(5, 3000.0));
        manager3level2.addSubordinate(getEmployeeElement(6, 2500.0));
        manager3level2.addSubordinate(getEmployeeElement(7, 3500.0));
        manager4level2.addSubordinate(getEmployeeElement(8, 2500.0));
        manager4level2.addSubordinate(getEmployeeElement(9, 3000.0));

        manager2level1.addSubordinate(getEmployeeElement(10, 3100.0));
        manager2level1.addSubordinate(getEmployeeElement(11, 3000.0));
        manager2level1.addSubordinate(getEmployeeElement(12, 5000.0));
        manager2level1.addSubordinate(getEmployeeElement(13, 1000.0));
        manager2level1.addSubordinate(getEmployeeElement(14, 3000.0));
        manager2level1.addSubordinate(getEmployeeElement(15, 3000.0));
        return ceo;
    }

    public static EmployeeTreeElement get3ManagersWithTooLowViolation() {
        EmployeeTreeElement ceo = getEmployeeElement(0, 1.0);
        EmployeeTreeElement manager1Level1 = getEmployeeElement(1, 5000.0);
        EmployeeTreeElement manager2level1 = getEmployeeElement(2, 3000.0);
        ceo.addSubordinates(List.of(manager1Level1, manager2level1));

        EmployeeTreeElement manager3level2 = getEmployeeElement(3, 4000.0);
        EmployeeTreeElement manager4level2 = getEmployeeElement(4, 3000.0);
        manager1Level1.addSubordinates(List.of(manager3level2, manager4level2));
        manager3level2.addSubordinate(getEmployeeElement(5, 3000.0));
        manager3level2.addSubordinate(getEmployeeElement(6, 2500.0));
        manager3level2.addSubordinate(getEmployeeElement(7, 3500.0));
        manager4level2.addSubordinate(getEmployeeElement(8, 2500.0));
        manager4level2.addSubordinate(getEmployeeElement(9, 3000.0));

        manager2level1.addSubordinate(getEmployeeElement(10, 3100.0));
        manager2level1.addSubordinate(getEmployeeElement(11, 3000.0));
        manager2level1.addSubordinate(getEmployeeElement(12, 5000.0));
        manager2level1.addSubordinate(getEmployeeElement(13, 1000.0));
        manager2level1.addSubordinate(getEmployeeElement(14, 3000.0));
        manager2level1.addSubordinate(getEmployeeElement(15, 3000.0));
        return ceo;
    }

    public static EmployeeTreeElement getTooBigChainViolation() {
        EmployeeTreeElement ceo = getEmployeeElement(0);
        EmployeeTreeElement manager1Level1 = getEmployeeElement(1);
        EmployeeTreeElement manager2level1 = getEmployeeElement(2);
        ceo.addSubordinates(List.of(manager1Level1, manager2level1));

        EmployeeTreeElement manager3level2 = getEmployeeElement(3);
        EmployeeTreeElement manager4level2 = getEmployeeElement(4);
        manager1Level1.addSubordinates(List.of(manager3level2, manager4level2));
        EmployeeTreeElement manager5level3 = getEmployeeElement(5);
        manager3level2.addSubordinate(manager5level3);
        manager3level2.addSubordinate(getEmployeeElement(6));
        manager3level2.addSubordinate(getEmployeeElement(7));
        EmployeeTreeElement manager6Level3 = getEmployeeElement(8);
        manager4level2.addSubordinate(manager6Level3);

        EmployeeTreeElement manager7Level4 = getEmployeeElement(9);
        EmployeeTreeElement manager8Level4 = getEmployeeElement(10);
        manager5level3.addSubordinates(List.of(manager7Level4, manager8Level4));
        EmployeeTreeElement manager9Level4 = getEmployeeElement(11);
        manager6Level3.addSubordinate(manager9Level4);
        EmployeeTreeElement manager10Level5 = getEmployeeElement(12);
        manager7Level4.addSubordinate(manager10Level5);
        EmployeeTreeElement manager11Level5 = getEmployeeElement(13);
        manager9Level4.addSubordinate(manager11Level5);
        EmployeeTreeElement manager12Level6 = getEmployeeElement(14);
        EmployeeTreeElement manager13Level6 = getEmployeeElement(15);
        manager11Level5.addSubordinates(List.of(manager12Level6, manager13Level6));

        manager12Level6.addSubordinates(List.of(getEmployeeElement(16), getEmployeeElement(17)));

        return ceo;
    }

    public static Employee getEmployee(int id) {
        return getEmployee(id, 0);
    }

    public static EmployeeTreeElement getEmployeeElement(int id) {
        return getEmployeeElement(id, 0);
    }

    private static EmployeeTreeElement getEmployeeElement(int id, double salary) {
        return new EmployeeTreeElement(getEmployee(id, salary));
    }

    private static Employee getEmployee(int id, double salary) {
        return new Employee(id, "Doe" + id, "Joe", salary);
    }
}
