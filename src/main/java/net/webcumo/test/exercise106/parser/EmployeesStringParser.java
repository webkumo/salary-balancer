package net.webcumo.test.exercise106.parser;

import net.webcumo.test.exercise106.employee.Employee;
import net.webcumo.test.exercise106.exceptions.parser.CannotParseNumberException;
import net.webcumo.test.exercise106.exceptions.parser.IncorrectFieldsCountException;

import java.util.stream.Stream;

public class EmployeesStringParser implements EmployeesSource {
    private static final int FIELDS_COUNT = 5;
    private static final int CEO_FIELDS_COUNT = 4;
    private static final String DELIMITER = ",";
    private static final int FIRST_NAME_INDEX = 1;
    private static final int LAST_NAME_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final int PARENT_ID_INDEX = 4;
    private static final int ID_INDEX = 0;

    private final EmployeeStringsSource stringSource;

    public EmployeesStringParser(EmployeeStringsSource employeeStringsSource) {
        this.stringSource = employeeStringsSource;
    }

    @Override
    public Stream<EmployeeWithParentId> getEmployees() {
        return stringSource.getStrings()
                .map(this::parse);
    }

    private EmployeeWithParentId parse(String inputString) {
        String[] fields = inputString.split(DELIMITER);
        validate(inputString, fields);
        try {
            Employee employee = new Employee(getId(fields),
                    getLastName(fields),
                    getFirstName(fields),
                    getSalary(fields));
            return new EmployeeWithParentId(employee, getParentId(fields));
        } catch (NumberFormatException e) {
            throw new CannotParseNumberException(e, inputString);
        }
    }

    private void validate(String employee, String[] fields) {
        if (fields.length != FIELDS_COUNT && fields.length != CEO_FIELDS_COUNT) {
            throw new IncorrectFieldsCountException(employee, fields.length);
        }
    }

    private static long getId(String[] fields) throws NumberFormatException {
        return Long.parseLong(fields[ID_INDEX]);
    }

    private static String getLastName(String[] fields) {
        return fields[LAST_NAME_INDEX];
    }

    private static String getFirstName(String[] fields) {
        return fields[FIRST_NAME_INDEX];
    }

    private static double getSalary(String[] fields) {
        return Double.parseDouble(fields[SALARY_INDEX]);
    }

    private static Long getParentId(String[] fields) {
        return fields.length == CEO_FIELDS_COUNT
                ? null
                : Long.parseLong(fields[PARENT_ID_INDEX]);
    }
}
