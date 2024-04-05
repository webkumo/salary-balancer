package net.webcumo.test.exercise106;

import net.webcumo.test.exercise106.employee.EmployeeTreeBuilder;
import net.webcumo.test.exercise106.exceptions.DataProcessingException;
import net.webcumo.test.exercise106.exceptions.ErrorCodes;
import net.webcumo.test.exercise106.parser.EmployeeFileParser;
import net.webcumo.test.exercise106.parser.EmployeesStringParser;
import net.webcumo.test.exercise106.violationsearchers.ManagersSalaryTooHighSearcher;
import net.webcumo.test.exercise106.violationsearchers.ManagersSalaryTooLowSearcher;
import net.webcumo.test.exercise106.violationsearchers.TooLongResponsibilityChainSearcher;
import net.webcumo.test.exercise106.violationsearchers.ViolationSearcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalaryBalancerTest {
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
        EmployeeTreeBuilder builder =
                new EmployeeTreeBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        try {
            new SalaryBalancer(builder, violationSearchers, new ExceptionThrowerOnErrorCode()).run();
            fail();
        } catch (DataProcessingException e) {
            assertEquals(ErrorCodes.FILE_NOT_FOUND.getErrorCode(), e.getErrorCode());
            assertTrue(errCaptor.toString().contains(fileName));
        }
    }

    @Test
    void givenFileWithoutHeaderThenErrorMessage() {
        String fileName = "src/test/resources/no-headers.csv";
        EmployeeTreeBuilder builder =
                new EmployeeTreeBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        try {
            new SalaryBalancer(builder, violationSearchers, new ExceptionThrowerOnErrorCode()).run();
            fail();
        } catch (DataProcessingException e) {
            assertEquals(ErrorCodes.WRONG_FORMAT.getErrorCode(), e.getErrorCode());
            assertTrue(errCaptor.toString().contains("The header of file is wrong, should be:"));
        }
    }

    @Test
    void givenFileWithOnlyHeaderThenErrorMessage() {
        String fileName = "src/test/resources/headers.csv";
        EmployeeTreeBuilder builder =
                new EmployeeTreeBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        try {
            new SalaryBalancer(builder, violationSearchers, new ExceptionThrowerOnErrorCode()).run();
            fail();
        } catch (DataProcessingException e) {
            assertEquals(ErrorCodes.WRONG_FORMAT.getErrorCode(), e.getErrorCode());
            assertTrue(errCaptor.toString().contains("There is nothing to parse in the file"));
        }
    }

    @Test
    void givenFileWithOrphansThenErrorMessage() {
        String fileName = "src/test/resources/orphans.csv";
        EmployeeTreeBuilder builder =
                new EmployeeTreeBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        try {
            new SalaryBalancer(builder, violationSearchers, new ExceptionThrowerOnErrorCode()).run();
            fail();
        } catch (DataProcessingException e) {
            assertEquals(ErrorCodes.ORPHANS.getErrorCode(), e.getErrorCode());
            assertTrue(errCaptor.toString().contains("There was found orphans in file"));
        }
    }

    @Test
    void givenFileWithDuplicatesThenErrorMessage() {
        String fileName = "src/test/resources/duplicate.csv";
        EmployeeTreeBuilder builder =
                new EmployeeTreeBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        try {
            new SalaryBalancer(builder, violationSearchers, new ExceptionThrowerOnErrorCode()).run();
            fail();
        } catch (DataProcessingException e) {
            assertEquals(ErrorCodes.ALREADY_USED.getErrorCode(), e.getErrorCode());
            assertTrue(errCaptor.toString().contains("is already registered"));
        }
    }

    @Test
    void givenFileWith2CeoThenErrorMessage() {
        String fileName = "src/test/resources/2ceo.csv";
        EmployeeTreeBuilder builder =
                new EmployeeTreeBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        try {
            new SalaryBalancer(builder, violationSearchers, new ExceptionThrowerOnErrorCode()).run();
            fail();
        } catch (DataProcessingException e) {
            assertEquals(ErrorCodes.TOO_MANY_CEOS.getErrorCode(), e.getErrorCode());
            assertTrue(errCaptor.toString().contains("Found more than one CEOs"));
        }
    }

    @Test
    void givenFileWithNoCeoThenErrorMessage() {
        String fileName = "src/test/resources/no_ceo.csv";
        EmployeeTreeBuilder builder =
                new EmployeeTreeBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        try {
            new SalaryBalancer(builder, violationSearchers, new ExceptionThrowerOnErrorCode()).run();
            fail();
        } catch (DataProcessingException e) {
            assertEquals(ErrorCodes.NO_CEO.getErrorCode(), e.getErrorCode());
            assertTrue(errCaptor.toString().contains("CEO was not found in the file"));
        }
    }

    @Test
    void givenFileWithNonNumberInNumericFieldThenErrorMessage() {
        String fileName = "src/test/resources/non_number.csv";
        EmployeeTreeBuilder builder =
                new EmployeeTreeBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        try {
            new SalaryBalancer(builder, violationSearchers, new ExceptionThrowerOnErrorCode()).run();
            fail();
        } catch (DataProcessingException e) {
            assertEquals(ErrorCodes.WRONG_FORMAT.getErrorCode(), e.getErrorCode());
            assertTrue(errCaptor.toString().contains("Cannot parse number:"));
        }
    }

    @Test
    void givenFileWithIncorrectFieldsCountThenErrorMessage() {
        String fileName = "src/test/resources/wrong_fields.csv";
        EmployeeTreeBuilder builder =
                new EmployeeTreeBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
        try {
            new SalaryBalancer(builder, violationSearchers, new ExceptionThrowerOnErrorCode()).run();
            fail();
        } catch (DataProcessingException e) {
            assertEquals(ErrorCodes.WRONG_FORMAT.getErrorCode(), e.getErrorCode());
            assertTrue(errCaptor.toString().contains("count of fields"));
        }
    }

    @Test
    void givenCorrectFileWithViolationsThenOnlyMainOutput() {
        String fileName = "src/test/resources/sample.csv";
        EmployeeTreeBuilder builder =
                new EmployeeTreeBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
            new SalaryBalancer(builder, violationSearchers, new ExceptionThrowerOnErrorCode()).run();
        assertEquals(0, errCaptor.size());
        assertTrue(outCaptor.toString()
                .contains("Manager Chekov Martin (124) has lower than 120% of it's subordinates average."));
    }

    @Test
    void givenCorrectFileWithoutViolationsThenOnlyMainOutput() {
        String fileName = "src/test/resources/correct.csv";
        EmployeeTreeBuilder builder =
                new EmployeeTreeBuilder(new EmployeesStringParser(new EmployeeFileParser(fileName)));
        List<ViolationSearcher> violationSearchers = List.of(new ManagersSalaryTooLowSearcher(),
                new ManagersSalaryTooHighSearcher(),
                new TooLongResponsibilityChainSearcher());
            new SalaryBalancer(builder, violationSearchers, new ExceptionThrowerOnErrorCode()).run();
        assertEquals(0, errCaptor.size());
        assertEquals(0, outCaptor.size());
    }
}