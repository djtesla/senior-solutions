package employees;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class EmployeesRepositoryTest {

    @Autowired
    EmployeesRepository employeesRepository;

    @Test
    void testPersist() {
        Employee employee = new Employee("Buldoser Doe");
        employeesRepository.save(employee);
        List<Employee> employees =  employeesRepository.findAll();
        assertThat(employees).extracting(Employee::getName).containsExactly("Buldoser Doe");

    }

    @Test
    void testPersistThanFindByName() {
        Employee employee1 = new Employee("Buldoser Doe");
        Employee employee2 = new Employee("Chuck Doe");
        employeesRepository.save(employee1);
        employeesRepository.save(employee2);
        Employee employee =  employeesRepository.findEmployeeByName("Chuck Doe");
        assertThat(employee.getName()).startsWith("Chuck");
    }


}
