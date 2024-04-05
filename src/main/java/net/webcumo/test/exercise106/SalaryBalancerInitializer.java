package net.webcumo.test.exercise106;

import net.webcumo.test.exercise106.employee.EmployeeTreeBuilder;
import net.webcumo.test.exercise106.parser.EmployeeFileParser;
import net.webcumo.test.exercise106.parser.EmployeesStringParser;
import net.webcumo.test.exercise106.violationsearchers.ManagersSalaryTooHighSearcher;
import net.webcumo.test.exercise106.violationsearchers.ManagersSalaryTooLowSearcher;
import net.webcumo.test.exercise106.violationsearchers.TooLongResponsibilityChainSearcher;
import net.webcumo.test.exercise106.violationsearchers.ViolationSearcher;

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

    static EmployeeTreeBuilder configureBuilder(String fileName) {
        return new EmployeeTreeBuilder(
                new EmployeesStringParser(
                        new EmployeeFileParser(fileName)));
    }

    static List<ViolationSearcher> configureViolationSearchers() {
        return List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
    }
}
