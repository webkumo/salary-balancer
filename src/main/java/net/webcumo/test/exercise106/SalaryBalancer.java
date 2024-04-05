package net.webcumo.test.exercise106;

import net.webcumo.test.exercise106.employee.EmployeeTreeBuilder;
import net.webcumo.test.exercise106.employee.EmployeeTreeElement;
import net.webcumo.test.exercise106.exceptions.DataProcessingException;
import net.webcumo.test.exercise106.violationsearchers.ViolationSearcher;

import java.util.List;

public class SalaryBalancer implements Runnable {
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