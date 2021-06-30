package employees;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

    private AtomicLong idGenerator = new AtomicLong();

    private ModelMapper modelMapper;

    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
            new Employee(1L, "John Doe"),
            new Employee(2L, "Jack Doe"))));

    public EmployeesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
        List<Employee> employeesFiltered = employees.stream().filter(e -> prefix.isEmpty() || e.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .collect(Collectors.toList());
        return modelMapper.map(employeesFiltered, targetListType);
    }


    public EmployeeDto listEmployeesById(long id) {
        Employee employee = employees.stream().filter(e->e.getId()== id)
                .findAny().orElseThrow(() -> new IllegalStateException("Employee not found: " + id ));
        return modelMapper.map(employee,EmployeeDto.class);
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(idGenerator.incrementAndGet(),command.getName());
        employees.add(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public EmployeeDto updateEmployee(UpdateEmployeeCommand command, long id) {
        Employee employee = employees.stream()
                .filter(e->e.getId()==id).findAny().orElseThrow(
                ()->new IllegalArgumentException ("Cannot find employee wit id " +id));
        employee.setName(command.getName());
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        Employee employee = employees.stream()
                .filter(e->e.getId()==id).findAny().orElseThrow(
                        ()->new IllegalArgumentException ("Cannot find employee wit id " +id));
        employees.remove(employee);

    }
}
