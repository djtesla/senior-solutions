package empapp.layers;

import empapp.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public Optional<Employee> findEmployeeByName(String name) {
        return employees
                .stream()
                .filter(employee -> employee.getName().equals(name))
                .findFirst();
    }


    public void saveEmployee(Employee employee) {
        System.out.println("PRÓBA");
        employees.add(employee);
    }
}
