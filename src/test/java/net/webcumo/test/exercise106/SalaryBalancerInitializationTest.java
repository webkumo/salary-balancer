package net.webcumo.test.exercise106;

import net.webcumo.test.exercise106.employee.Employee;
import net.webcumo.test.exercise106.violations.ManagersSalaryTooHighSearcher;
import net.webcumo.test.exercise106.violations.ManagersSalaryTooLowSearcher;
import net.webcumo.test.exercise106.violations.TooLongResponsibilityChainSearcher;
import net.webcumo.test.exercise106.violations.ViolationSearcher;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalaryBalancerInitializationTest {

    @Test
    void givenArgsWithFileNameThenReturnIt() {
        String fileName = "some file name";
        String[] args = {fileName};
        assertEquals(fileName, SalaryBalancerInitializer.getFileName(args));
    }

    @Test
    void givenNoArgsThenReturnDefault() {
        String[] args = {};
        assertEquals(SalaryBalancerInitializer.DEFAULT_FILE_NAME,
                SalaryBalancerInitializer.getFileName(args));
    }

    @Test
    void givenSearchersRequiredThenConfigured() {
        List<ViolationSearcher<Employee>> configured =
                SalaryBalancerInitializer.configureViolationSearchers();
        assertEquals(1, configured.stream()
                .filter(ManagersSalaryTooHighSearcher.class::isInstance)
                .count());
        assertEquals(1, configured.stream()
                .filter(ManagersSalaryTooLowSearcher.class::isInstance)
                .count());
        assertEquals(1, configured.stream()
                .filter(TooLongResponsibilityChainSearcher.class::isInstance)
                .count());
    }

}