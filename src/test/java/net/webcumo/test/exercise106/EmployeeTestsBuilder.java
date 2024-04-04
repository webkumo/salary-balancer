package net.webcumo.test.exercise106;

import net.webcumo.test.exercise106.employee.Employee;

import java.util.List;

public class EmployeeTestsBuilder {
    public static Employee getNotManager() {
        return getEmployee(0, 1000.0);
    }

    public static Employee getManagerWithoutViolations() {
        Employee ceo = getEmployee(0, 6000.0);
        Employee manager1Level1 = getEmployee(1, 5000.0);
        Employee manager2level1 = getEmployee(2, 4000.0);
        ceo.addSubordinates(List.of(manager1Level1, manager2level1));

        Employee manager3level2 = getEmployee(3, 4000.0);
        Employee manager4level2 = getEmployee(4, 3500.0);
        manager1Level1.addSubordinates(List.of(manager3level2, manager4level2));
        manager3level2.addSubordinate(getEmployee(5, 3000.0));
        manager3level2.addSubordinate(getEmployee(6, 2500.0));
        manager3level2.addSubordinate(getEmployee(7, 3500.0));
        manager4level2.addSubordinate(getEmployee(8, 2500.0));
        manager4level2.addSubordinate(getEmployee(9, 3000.0));

        manager2level1.addSubordinate(getEmployee(10, 3100.0));
        manager2level1.addSubordinate(getEmployee(11, 3000.0));
        manager2level1.addSubordinate(getEmployee(12, 5000.0));
        manager2level1.addSubordinate(getEmployee(13, 1000.0));
        manager2level1.addSubordinate(getEmployee(14, 3000.0));
        manager2level1.addSubordinate(getEmployee(15, 3000.0));
        return ceo;
    }

    public static Employee get3ManagersWithTooHighViolation() {
        Employee ceo = getEmployee(0, 600000.0);
        Employee manager1Level1 = getEmployee(1, 5000.0);
        Employee manager2level1 = getEmployee(2, 40000.0);
        ceo.addSubordinates(List.of(manager1Level1, manager2level1));

        Employee manager3level2 = getEmployee(3, 40000.0);
        Employee manager4level2 = getEmployee(4, 3500.0);
        manager1Level1.addSubordinates(List.of(manager3level2, manager4level2));
        manager3level2.addSubordinate(getEmployee(5, 3000.0));
        manager3level2.addSubordinate(getEmployee(6, 2500.0));
        manager3level2.addSubordinate(getEmployee(7, 3500.0));
        manager4level2.addSubordinate(getEmployee(8, 2500.0));
        manager4level2.addSubordinate(getEmployee(9, 3000.0));

        manager2level1.addSubordinate(getEmployee(10, 3100.0));
        manager2level1.addSubordinate(getEmployee(11, 3000.0));
        manager2level1.addSubordinate(getEmployee(12, 5000.0));
        manager2level1.addSubordinate(getEmployee(13, 1000.0));
        manager2level1.addSubordinate(getEmployee(14, 3000.0));
        manager2level1.addSubordinate(getEmployee(15, 3000.0));
        return ceo;
    }

    public static Employee get3ManagersWithTooLowViolation() {
        Employee ceo = getEmployee(0, 1.0);
        Employee manager1Level1 = getEmployee(1, 5000.0);
        Employee manager2level1 = getEmployee(2, 3000.0);
        ceo.addSubordinates(List.of(manager1Level1, manager2level1));

        Employee manager3level2 = getEmployee(3, 4000.0);
        Employee manager4level2 = getEmployee(4, 3000.0);
        manager1Level1.addSubordinates(List.of(manager3level2, manager4level2));
        manager3level2.addSubordinate(getEmployee(5, 3000.0));
        manager3level2.addSubordinate(getEmployee(6, 2500.0));
        manager3level2.addSubordinate(getEmployee(7, 3500.0));
        manager4level2.addSubordinate(getEmployee(8, 2500.0));
        manager4level2.addSubordinate(getEmployee(9, 3000.0));

        manager2level1.addSubordinate(getEmployee(10, 3100.0));
        manager2level1.addSubordinate(getEmployee(11, 3000.0));
        manager2level1.addSubordinate(getEmployee(12, 5000.0));
        manager2level1.addSubordinate(getEmployee(13, 1000.0));
        manager2level1.addSubordinate(getEmployee(14, 3000.0));
        manager2level1.addSubordinate(getEmployee(15, 3000.0));
        return ceo;
    }

    public static Employee getTooBigChainViolation() {
        Employee ceo = getEmployee(0);
        Employee manager1Level1 = getEmployee(1);
        Employee manager2level1 = getEmployee(2);
        ceo.addSubordinates(List.of(manager1Level1, manager2level1));

        Employee manager3level2 = getEmployee(3);
        Employee manager4level2 = getEmployee(4);
        manager1Level1.addSubordinates(List.of(manager3level2, manager4level2));
        Employee manager5level3 = getEmployee(5);
        manager3level2.addSubordinate(manager5level3);
        manager3level2.addSubordinate(getEmployee(6));
        manager3level2.addSubordinate(getEmployee(7));
        Employee manager6Level3 = getEmployee(8);
        manager4level2.addSubordinate(manager6Level3);

        Employee manager7Level4 = getEmployee(9);
        Employee manager8Level4 = getEmployee(10);
        manager5level3.addSubordinates(List.of(manager7Level4, manager8Level4));
        Employee manager9Level4 = getEmployee(11);
        manager6Level3.addSubordinate(manager9Level4);
        Employee manager10Level5 = getEmployee(12);
        manager7Level4.addSubordinate(manager10Level5);
        Employee manager11Level5 = getEmployee(13);
        manager9Level4.addSubordinate(manager11Level5);
        Employee manager12Level6 = getEmployee(14);
        Employee manager13Level6 = getEmployee(15);
        manager11Level5.addSubordinates(List.of(manager12Level6, manager13Level6));

        manager12Level6.addSubordinates(List.of(getEmployee(16), getEmployee(17)));

        return ceo;
    }

    public static Employee getEmployee(int id) {
        return getEmployee(id, 0);
    }

    private static Employee getEmployee(int id, double salary) {
        return new Employee(id, "Doe" + id, "Joe", salary);
    }
}
