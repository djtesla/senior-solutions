package empapp;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    EmployeeService employeeService = new EmployeeService();

    @Test
    void testListEmployees () {
        List<Employee> employees = employeeService.listEmployees();
        assertEquals(List.of(new Employee("John Doe",1970), new Employee("Jane Doe",1980)), employees);


    }

    @Test
    void testListEmployeeNames () {
        assertEquals(List.of("John Doe", "Jane Doe"), employeeService.listEmployees().stream()
                .map(Employee::getName)
                .collect(Collectors.toList()));


    }

    @Test
    void testCalculate() {
        //assertTimeout(Duration.ofSeconds(3), ()-> employeeService.calculate());
        assertTimeoutPreemptively(Duration.ofSeconds(3), ()-> employeeService.calculate());

    }
}