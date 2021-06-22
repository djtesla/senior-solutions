package empapp.layers;

import empapp.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeService {

    EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeeRepository getRepository() {
        return repository;
    }


    public List<Employee> listEmployees(){
        return new ArrayList<>(repository.getEmployees());
    }

    public boolean createEmployee(String name, int yearOfBirth) {
        name = name.trim();
        Optional<Employee> employeeOptional = repository.findEmployeeByName(name);
        if (employeeOptional.isPresent()) {
            return false;
        } else {
            repository.saveEmployee(new Employee(name, yearOfBirth));
            return true;
        }
    }


}
