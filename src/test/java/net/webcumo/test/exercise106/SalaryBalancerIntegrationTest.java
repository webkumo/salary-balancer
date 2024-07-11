package net.webcumo.test.exercise106;

import net.webcumo.test.exercise106.employee.Employee;
import net.webcumo.test.exercise106.employee.EmployeeTreBuilder;
import net.webcumo.test.exercise106.exceptions.builder.ElementIdAlreadyUsedException;
import net.webcumo.test.exercise106.exceptions.builder.NoRootFoundException;
import net.webcumo.test.exercise106.exceptions.builder.OrphansFoundException;
import net.webcumo.test.exercise106.exceptions.builder.TooManyRootsException;
import net.webcumo.test.exercise106.exceptions.file.FileIOException;
import net.webcumo.test.exercise106.exceptions.file.FileIsEmptyException;
import net.webcumo.test.exercise106.exceptions.file.WrongHeaderException;
import net.webcumo.test.exercise106.exceptions.parser.CannotParseNumberException;
import net.webcumo.test.exercise106.exceptions.parser.IncorrectFieldsCountException;
import net.webcumo.test.exercise106.tree.TreeBuilder;
import net.webcumo.test.exercise106.parser.EmployeeFileParser;
import net.webcumo.test.exercise106.parser.EmployeesStringParser;
import net.webcumo.test.exercise106.violations.ManagersSalaryTooHighSearcher;
import net.webcumo.test.exercise106.violations.ManagersSalaryTooLowSearcher;
import net.webcumo.test.exercise106.violations.TooLongResponsibilityChainSearcher;
import net.webcumo.test.exercise106.violations.ViolationSearcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalaryBalancerIntegrationTest {
    private static final PrintStream DEFAULT_OUT = System.out;
    private static final PrintStream DEFAULT_ERR = System.err;

    private ByteArrayOutputStream outCaptor;
    private ByteArrayOutputStream errCaptor;


    @BeforeEach
    public void setUp() {
        outCaptor = new ByteArrayOutputStream();
        errCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outCaptor));
        System.setErr(new PrintStream(errCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(DEFAULT_OUT);
        System.setErr(DEFAULT_ERR);
    }

    @Test
    void givenNoFileThenErrorMessage() {
        String fileName = "the file not exists.csv";
        TreeBuilder<Employee> builder =
                new EmployeeTreBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher<Employee>> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        SalaryBalancer salaryBalancer = getSalaryBalancer(builder, violationSearchers);
        assertThrows(FileIOException.class, salaryBalancer::run);
    }

    @Test
    void givenFileWithoutHeaderThenErrorMessage() {
        String fileName = "src/test/resources/no-headers.csv";
        TreeBuilder<Employee> builder =
                new EmployeeTreBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher<Employee>> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        SalaryBalancer salaryBalancer = getSalaryBalancer(builder, violationSearchers);
        assertThrows(WrongHeaderException.class, salaryBalancer::run);
    }

    @Test
    void givenFileWithOnlyHeaderThenErrorMessage() {
        String fileName = "src/test/resources/headers.csv";
        TreeBuilder<Employee> builder =
                new EmployeeTreBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher<Employee>> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        SalaryBalancer salaryBalancer = getSalaryBalancer(builder, violationSearchers);
        assertThrows(FileIsEmptyException.class, salaryBalancer::run);
    }

    @Test
    void givenFileWithOrphansThenErrorMessage() {
        String fileName = "src/test/resources/orphans.csv";
        TreeBuilder<Employee> builder =
                new EmployeeTreBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher<Employee>> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        SalaryBalancer salaryBalancer = getSalaryBalancer(builder, violationSearchers);
        assertThrows(OrphansFoundException.class, salaryBalancer::run);
    }

    @Test
    void givenFileWithDuplicatesThenErrorMessage() {
        String fileName = "src/test/resources/duplicate.csv";
        TreeBuilder<Employee> builder =
                new EmployeeTreBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher<Employee>> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
                    SalaryBalancer salaryBalancer = getSalaryBalancer(builder, violationSearchers);
        assertThrows(ElementIdAlreadyUsedException.class, salaryBalancer::run);
    }

    @Test
    void givenFileWith2CeoThenErrorMessage() {
        String fileName = "src/test/resources/2ceo.csv";
        TreeBuilder<Employee> builder =
                new EmployeeTreBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher<Employee>> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        SalaryBalancer salaryBalancer = getSalaryBalancer(builder, violationSearchers);
        assertThrows(TooManyRootsException.class, salaryBalancer::run);
    }

    @Test
    void givenFileWithNoCeoThenErrorMessage() {
        String fileName = "src/test/resources/no_ceo.csv";
        TreeBuilder<Employee> builder =
                new EmployeeTreBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher<Employee>> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        SalaryBalancer salaryBalancer = getSalaryBalancer(builder, violationSearchers);
        assertThrows(NoRootFoundException.class, salaryBalancer::run);
    }

    @Test
    void givenFileWithNonNumberInNumericFieldThenErrorMessage() {
        String fileName = "src/test/resources/non_number.csv";
        TreeBuilder<Employee> builder =
                new EmployeeTreBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher<Employee>> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        SalaryBalancer salaryBalancer = getSalaryBalancer(builder, violationSearchers);
        assertThrows(CannotParseNumberException.class, salaryBalancer::run);
    }

    @Test
    void givenFileWithIncorrectFieldsCountThenErrorMessage() {
        String fileName = "src/test/resources/wrong_fields.csv";
        TreeBuilder<Employee> builder =
                new EmployeeTreBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher<Employee>> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        SalaryBalancer salaryBalancer = getSalaryBalancer(builder, violationSearchers);
        assertThrows(IncorrectFieldsCountException.class, salaryBalancer::run);
    }

    @Test
    void givenCorrectFileWithViolationsThenOnlyMainOutput() {
        String fileName = "src/test/resources/sample.csv";
        TreeBuilder<Employee> builder =
                new EmployeeTreBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher<Employee>> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
            getSalaryBalancer(builder, violationSearchers).run();
        assertEquals(0, errCaptor.size());
        assertTrue(outCaptor.toString()
                .contains("Manager Chekov Martin (124) has lower than 120% of it's subordinates average."));
    }

    @Test
    void givenCorrectFileWithoutViolationsThenOnlyMainOutput() {
        String fileName = "src/test/resources/correct.csv";
        TreeBuilder<Employee> builder =
                new EmployeeTreBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher<Employee>> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
            getSalaryBalancer(builder, violationSearchers).run();
        assertEquals(0, errCaptor.size());
        assertEquals(0, outCaptor.size());
    }

    private static SalaryBalancer getSalaryBalancer(TreeBuilder<Employee> builder, List<ViolationSearcher<Employee>> violationSearchers) {
        return new SalaryBalancer(builder,
                violationSearchers,
                new ExceptionThrowerOnErrorCode());
    }
}