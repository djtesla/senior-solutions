package empapp;

import empapp.layers.EmployeeRepository;
import empapp.layers.EmployeeService;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SoftAssertionsExtension.class)
public class EmployeeServiceWithAssertjTest {

    EmployeeService employeeService;

    @BeforeEach
    void init(){
        EmployeeRepository repository = new EmployeeRepository();
        repository.saveEmployee(new Employee("John Doe", 1980));
        repository.saveEmployee(new Employee("Jane Doe", 1970));
        repository.saveEmployee(new Employee("Jack Doe", 1951));
        employeeService = new EmployeeService(repository);
    }

    @Test
    public void testListEmployees(SoftAssertions softly){


        List<Employee> employees = employeeService.listEmployees();
        softly.assertThat(employees).hasSize(3).contains(new Employee("Jane Doe", 1970));

        softly.assertThat(employees).as("check if %s contains John Doe", employees.get(0)).extracting(Employee::getName).contains("Jhn Doe");

        softly.assertThat(employees).
                filteredOn(e->e.getName().contains("a")).hasSize(2);



    }

}
