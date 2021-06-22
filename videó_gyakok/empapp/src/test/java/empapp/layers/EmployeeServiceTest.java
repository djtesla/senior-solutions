package empapp.layers;

import empapp.Employee;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Test
    void testSaveEmployee() {
        EmployeeRepository repository = mock(EmployeeRepository.class);
        EmployeeService service = new EmployeeService(repository);

        //when(repository.findEmployeeByName(anyString())).thenReturn(Optional.of(new Employee("John Doe", 1970)));

        //assertTrue(service.createEmployee("John doe", 1970));
        assertTrue(service.createEmployee("John Doe", 1970));

        verify(repository).saveEmployee(argThat(employee -> employee.getName().equals("John Doe")));
        //verify(repository).saveEmployee(any());
        //verify(repository, never()).saveEmployee(any());


    }

}