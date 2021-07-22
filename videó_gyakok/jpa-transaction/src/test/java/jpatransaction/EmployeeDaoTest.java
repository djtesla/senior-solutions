package jpatransaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class EmployeeDaoTest {

    @Autowired
    EmployeeDao employeeDao;

    @Test
    void testSaveThenFind(){
        Employee employee = new Employee("John Doe");
        employeeDao.saveEmployee(employee);
        assertEquals("John Doe", employeeDao.findEmployeeById(employee.getId()).getName());
    }


    void testSaveLog(){}

}