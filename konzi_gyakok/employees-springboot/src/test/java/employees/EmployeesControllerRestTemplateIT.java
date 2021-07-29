package employees;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeesControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    EmployeesService employeesService;


    @Test
    public void testListEmployees() {
        employeesService.deleteAllEmployees();


        EmployeeDto employeeDto = template.postForObject("/api/employees", new CreateEmployeeCommand("John Doe"), EmployeeDto.class);
        assertThat(employeeDto.getName()).isEqualTo( "John Doe");

        template.postForObject("/api/employees", new CreateEmployeeCommand("Jane Doe"), EmployeeDto.class);

        List<EmployeeDto> employees =template.exchange("/api/employees", HttpMethod.GET, null, new ParameterizedTypeReference<List<EmployeeDto>>() {}).getBody();
        assertThat(employees).extracting("name").containsExactly("John Doe", "Jane Doe");

/*   EmployeeDto employeeDto = template.getForObject("/api/employees/1", EmployeeDto.class);
        assertThat(employeeDto).extracting("name").isEqualTo( "John Doe");

    template.postForObject("/api/employees", new CreateEmployeeCommand("Jane Doe"), EmployeeDto.class);
        List<EmployeeDto> employees =template.exchange("/api/employees", HttpMethod.GET, null, new ParameterizedTypeReference<List<EmployeeDto>>() {}).getBody();
        assertThat(employees).extracting("id", "name").contains(tuple(3l, "Jane Doe"));*/
    }
}
