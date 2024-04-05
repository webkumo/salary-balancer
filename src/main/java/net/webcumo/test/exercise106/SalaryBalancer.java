package net.webcumo.test.exercise106;

import net.webcumo.test.exercise106.employee.EmployeeTreeBuilder;
import net.webcumo.test.exercise106.employee.EmployeeTreeElement;
import net.webcumo.test.exercise106.exceptions.DataProcessingException;
import net.webcumo.test.exercise106.parser.EmployeeFileParser;
import net.webcumo.test.exercise106.parser.EmployeesStringParser;
import net.webcumo.test.exercise106.violationsearchers.ManagersSalaryTooLowSearcher;
import net.webcumo.test.exercise106.violationsearchers.ManagersSalaryTooHighSearcher;
import net.webcumo.test.exercise106.violationsearchers.TooLongResponsibilityChainSearcher;
import net.webcumo.test.exercise106.violationsearchers.ViolationSearcher;

import java.util.List;

public class SalaryBalancer implements Runnable {
    private static final String DEFAULT_FILE_NAME = "company.csv";

    public static void main(String[] args) {
        //todo this class plays the role of DI framework (as it impossible to use them according to rules)
        String fileName = args.length > 0 ? args[0] : DEFAULT_FILE_NAME;
        EmployeeTreeBuilder builder =
                new EmployeeTreeBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        new SalaryBalancer(builder, violationSearchers, new ExitOnErrorCode()).run();
    }

    private final EmployeeTreeBuilder builder;
    private final List<ViolationSearcher> violationSearchers;
    private final ErrorCodeListener errorCodeListener;

    public SalaryBalancer(EmployeeTreeBuilder builder,
                          List<ViolationSearcher> violationSearchers,
                          ErrorCodeListener errorCodeListener) {
        this.builder = builder;
        this.violationSearchers = violationSearchers;
        this.errorCodeListener = errorCodeListener;
    }

    @Override
    public void run() {
        try {
            EmployeeTreeElement ceo = builder.build();
            violationSearchers.stream().parallel()
                    .forEach(searcher -> searcher.searchViolations(ceo));
        } catch (DataProcessingException e) {
            System.err.println(e.getMessage());
            errorCodeListener.registerErrorCode(e.getErrorCode());
        }
    }
}