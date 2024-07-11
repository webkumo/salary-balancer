package net.webcumo.test.exercise106;

import net.webcumo.test.exercise106.employee.Employee;
import net.webcumo.test.exercise106.employee.EmployeeTreBuilder;
import net.webcumo.test.exercise106.tree.TreeBuilder;
import net.webcumo.test.exercise106.parser.EmployeeFileParser;
import net.webcumo.test.exercise106.parser.EmployeesStringParser;
import net.webcumo.test.exercise106.violations.ManagersSalaryTooHighSearcher;
import net.webcumo.test.exercise106.violations.ManagersSalaryTooLowSearcher;
import net.webcumo.test.exercise106.violations.TooLongResponsibilityChainSearcher;
import net.webcumo.test.exercise106.violations.ViolationSearcher;

import java.util.List;

public class SalaryBalancerInitializer {
    static final String DEFAULT_FILE_NAME = "company.csv";

    public static void main(String[] args) {
        //todo this class plays the role of DI framework (as it impossible to use them according to rules)
        configureSalaryBalancer(getFileName(args)).run();
    }

    static String getFileName(String[] args) {
        return args.length > 0 ? args[0] : DEFAULT_FILE_NAME;
    }

    static SalaryBalancer configureSalaryBalancer(String fileName) {
        return new SalaryBalancer(configureBuilder(fileName),
                configureViolationSearchers(),
                new ExitOnErrorCode());
    }

    static TreeBuilder<Employee> configureBuilder(String fileName) {
        return new EmployeeTreBuilder(
                new EmployeesStringParser(
                        new EmployeeFileParser(fileName)));
    }

    static List<ViolationSearcher<Employee>> configureViolationSearchers() {
        return List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
    }
}
