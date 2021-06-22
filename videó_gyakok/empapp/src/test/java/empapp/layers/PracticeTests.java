package empapp.layers;

import empapp.Employee;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)

@ExtendWith(MockitoExtension.class)
public class PracticeTests implements PrintNameCapable {


    @Mock
    EmployeeRepository repository;

    @InjectMocks
    EmployeeService employeeService;


    Employee employee;
    private int values[][] = {{51, 2021}, {41, 2011}, {31, 2001}};

    public PracticeTests() {
        System.out.println("KONSTRUKTOR");
        employee = new Employee("John Doe", 1970);

    }

    @BeforeAll
    static void run() {
        System.out.println("BEFORE ALL");
    }


/*    @Test
    public void testGetAge() {
        //Given - előkészízés: példányosítás és megfelelő állapotba hozás
        Employee employee1 = new Employee("John Doe", 1980);

        //When - tesztelendő metódus meghívsása
        int age = employee1.getAge(2021);

        //Then
        assertEquals(41, age);

        //Egybevonva
        assertEquals(41, new Employee("John Doe", 1980).getAge(2021));
    }*/

    @Test
    @Order(2)
        //@Disabled
    void testGetAgeWithInit(TestInfo testInfo) {
        assumeTrue(0 == 1);

        assertEquals(41, employee.getAge(2021), "Elbuktam; age 41!");
    }

    @Test
    @Order(1)
    void test_Get_Age_With_Init2() {
        System.out.println("Test2");
        assertEquals(30, employee.getAge(2000));
    }

    @Test
    void collectionTest() {
        List<Employee> employeeList = List.of(new Employee("John Doe", 1980),
                new Employee("Jane Doe", 1970));

        assertEquals(List.of("John Doe", "Jane Doe"),
                employeeList.stream().map(Employee::getName).collect(Collectors.toList()));
    }

    @Test
    void testReferenceEqualities() {
        EmployeeService service = new EmployeeService(new EmployeeRepository());
        service.repository.saveEmployee(new Employee("John Doe", 1970));
        service.repository.saveEmployee(new Employee("Jane Doe", 1980));

        assertEquals(List.of(new Employee("John Doe", 1970), new Employee("Jane Doe", 1980)),
                service.repository.getEmployees());
    }

    @Test
    void testAssertAll() {
        //fail();
        assertAll(
                () -> assertEquals("John Doe", employee.getName()),
                () -> assertEquals(51, employee.getAge(2021))
        );
    }

    @Test
    @RepeatedTest(value = 3, name = "Exception test {currentRepetition}/{totalRepetitions}")
    void testException() {
        assertThrows(IllegalArgumentException.class, () -> new Employee("Gézu", 1940));
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> new Employee("Gézu", 1940));
        assertEquals("Guy is too old.", iae.getMessage());
    }

    @Test
    void testTimeout() {
        assertTimeoutPreemptively(Duration.ofSeconds(6), () -> employee.calculate());
    }


    @Test
    @RepeatedTest(value = 3, name = "Get Age test {currentRepetition}/{totalRepetitions}")
    void testRepetitionInfo(RepetitionInfo repetitionInfo) {

        assertEquals(values[repetitionInfo.getCurrentRepetition() - 1][0],
                employee.getAge(values[repetitionInfo.getCurrentRepetition() - 1][1]));

    }

    @Test
    void mockTest() {

     /*   EmployeeRepository repository = mock(EmployeeRepository.class);
        employeeService = new EmployeeService(repository);*/
        //when(repository.findEmployeeByName(anyString())).thenReturn(Optional.of(new Employee("John Doe", 1970)));
        assertTrue(employeeService.createEmployee("  John Doe  ", 1980));
        verify(repository).saveEmployee(argThat(employee -> employee.getName().equals("John Doe") ));
        //verify(repository,never()).saveEmployee(any());
    }


}


