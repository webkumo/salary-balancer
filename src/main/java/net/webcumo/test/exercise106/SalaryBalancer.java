package net.webcumo.test.exercise106;

import net.webcumo.test.exercise106.employee.Employee;
import net.webcumo.test.exercise106.tree.TreeBuilder;
import net.webcumo.test.exercise106.tree.TreeElement;
import net.webcumo.test.exercise106.exceptions.DataProcessingException;
import net.webcumo.test.exercise106.violations.ViolationSearcher;

import java.util.List;

public class SalaryBalancer implements Runnable {
    private final TreeBuilder<Employee> builder;
    private final List<ViolationSearcher<Employee>> violationSearchers;
    private final ErrorCodeListener errorCodeListener;

    public SalaryBalancer(TreeBuilder<Employee> builder,
                          List<ViolationSearcher<Employee>> violationSearchers,
                          ErrorCodeListener errorCodeListener) {
        this.builder = builder;
        this.violationSearchers = violationSearchers;
        this.errorCodeListener = errorCodeListener;
    }

    @Override
    public void run() {
        try {
            TreeElement<Employee> ceo = builder.build();
            violationSearchers.stream().parallel()
                    .forEach(searcher -> searcher.searchViolations(ceo));
        } catch (DataProcessingException e) {
            System.err.println(e.getMessage());
            errorCodeListener.registerErrorCode(e);
        }
    }
}