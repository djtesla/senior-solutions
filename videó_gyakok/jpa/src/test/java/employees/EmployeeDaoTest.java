package employees;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeDaoTest {

    private EmployeeDao employeeDao;
    private  ParkingPlaceDao parkingPlaceDao;


    @BeforeEach
    void init() {
     /*   MariaDbDataSource dataSource;
        try {
            dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/employees?useUnicode=true");
            dataSource.setUser("employees");
            dataSource.setPassword("employees");
        } catch (SQLException sqe) {
            throw new IllegalStateException("Cannot connect to database", sqe);
        }

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();*/


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        employeeDao = new EmployeeDao(entityManagerFactory);
        parkingPlaceDao = new ParkingPlaceDao(entityManagerFactory);

    }

    @Test
    public void testSaveThenFind() {
        Employee employee = new Employee("Jack Doe");
        employeeDao.save(employee);
        long id = employee.getId();
        Employee anotherEmployee = employeeDao.findById(id);
        assertEquals("Jack Doe", anotherEmployee.getName());
    }

    @Test
    public void testSaveThenListAll() {
        employeeDao.save(new Employee("Jack Doe"));
        employeeDao.save(new Employee("Jane Doe"));
        List<String> names = employeeDao.listAll().stream().map(Employee::getName).collect(Collectors.toList());
        assertEquals(List.of("Jack Doe", "Jane Doe"), names);
    }

    @Test
    public void testChangeName() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);
        long id = employee.getId();
        employeeDao.updateEmployeeName(id, "Jimmy Doe");
        Employee another = employeeDao.findById(id);
        assertEquals("Jimmy Doe", another.getName());
    }

    @Test
    public void testDelete() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);
        long id = employee.getId();
        employeeDao.delete(id);
        List<Employee> employees = employeeDao.listAll();
        assertTrue(employees.isEmpty());
    }

    @Test
    void testEmployeeWithAttributes() {
        for (int i = 0; i < 10; i++) {
            employeeDao.save(new Employee("Jimmy Doe " + i, Employee.EmployeeType.HALF_TIME, LocalDate.of(2000, 10, 10)));
        }

        //assertEquals(LocalDate.of(2000, 10, 10), employeeDao.listAll().get(0).getDateOfBirth());
    }

    @Test
    void testSaveEmployeeChangeState() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);
        employee.setName("Jimmy Doe"); //már detached
        Employee modifiedEmployee = employeeDao.findById(employee.getId());
        assertEquals("John Doe", modifiedEmployee.getName());
        assertFalse(employee == modifiedEmployee);

    }

    @Test
    void testMerge() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);
        employee.setName("Jimmy Doe"); //már detached; csatolja vissza a módosítást!
        employeeDao.updateEmployee(employee);
        Employee modifiedEmployee = employeeDao.findById(employee.getId());
        assertEquals("Jimmy Doe", modifiedEmployee.getName());
        assertFalse(employee == modifiedEmployee);
    }

    @Test
    void testFlush() {
        for (int i = 1; i < 11; i++) {
            employeeDao.save(new Employee("John Doe " + i));
        }
        employeeDao.updateEmployeeNames();
    }


    @Test
    void testNickNames() {
        Employee employee = new Employee("John Doe");
        employee.setNickNames(Set.of("Johnny", "J"));
        employeeDao.save(employee);
        Employee another = employeeDao.findById(employee.getId());
        assertEquals(Set.of("Johnny", "J"), another.getNickNames());
    }

    @Test
    void testFindEmployeeByIdWithNickNames() {
        Employee employee = new Employee("John Doe");
        employee.setNickNames(Set.of("Johnny", "J"));
        employeeDao.save(employee);
        Employee another = employeeDao.findEmployeeByIdWithNickNames(employee.getId());
        assertEquals(Set.of("Johnny", "J"), another.getNickNames());
    }

    @Test
    void testVacations() {
        Employee employee = new Employee("John Doe");
        employee.setVacationBookings(Set.of
                (new VacationEntry(LocalDate.of(2021, 7, 11), 7),
                        new VacationEntry(LocalDate.of(2021, 9, 3), 10)));
        employeeDao.save(employee);
        Employee another = employeeDao.findEmployeeByIdWithVacations(employee.getId());
        assertEquals(2, another.getVacationBookings().size());
    }

    @Test
    void testPhoneNumbers() {
        Employee employee = new Employee("John Doe");
        PhoneNumber number1 = new PhoneNumber("home", "1234");
        PhoneNumber number2 = new PhoneNumber("work", "5678");
        employee.addPhoneNumber(number1);
        employee.addPhoneNumber(number2);
        employeeDao.save(employee);
        Employee another = employeeDao.findById(employee.getId());
        assertEquals(2, another.getPhoneNumbers().size());
    }

    @Test
    void testAddPhoneNumbers() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);
        employeeDao.addPhoneNumber(employee.getId(), new PhoneNumber("home", "1234"));
        Employee another = employeeDao.findEmployeeByIdWithPhoneNumbers(employee.getId());
        assertEquals(1, another.getPhoneNumbers().size());
    }


    @Test
    void testRemove() {
        Employee employee = new Employee("John Doe");
        PhoneNumber number1 = new PhoneNumber("home", "1234");
        PhoneNumber number2 = new PhoneNumber("work", "5678");
        employee.addPhoneNumber(number1);
        employee.addPhoneNumber(number2);
        employeeDao.save(employee);
        employeeDao.delete(employee.getId());
    }

    @Test
    void findEmployeeByName() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);
        Employee another = employeeDao.findById(employee.getId());
        assertEquals("John Doe", another.getName());
    }

    @Test
    void testListEmployees() {
        for (int i = 1; i < 21; i++) {
            employeeDao.save(new Employee("John Doe " + i));
        }
        List<Employee> employees = employeeDao.listEmployees(10, 10);
        System.out.println(employees);
        assertEquals("John Doe 11", employees.get(0).getName());
    }


    @Test
    void testListEmployeesWithNamedQuery() {
        for (int i=1; i<21; i++) {
            employeeDao.save(new Employee("John Doe " + i));
        }
        List<Employee> employees = employeeDao.listEmployeesWithNamedQuery();
        assertEquals(20, employees.size());
    }

    @Test
    void testFindParkingPlaceNumberByEmployeeName(){
        Employee employee = new Employee("John Doe");
        ParkingPlace parkingPlace = new ParkingPlace(123);
        parkingPlaceDao.saveParkingPlace(parkingPlace);
        employee.setParkingPlace(parkingPlace);
        employeeDao.save(employee);
        int number = employeeDao.findParkingPlaceNumberByEmployeeName(employee.getName());
        assertEquals(123, number);
    }

    @Test
    void testListEmployeeBaseData(){
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);
        List<Object[]> data = employeeDao.listEmployeeBaseData();
        assertEquals("John Doe", data.get(0)[1]);
    }

    @Test
    void testDto(){
        employeeDao.save( new Employee("John Doe"));
        employeeDao.save( new Employee("Jack Doe"));
        List<EmpBaseDataDto> dtoList = employeeDao.listEmployeeDto();
        assertEquals("John Doe", dtoList.get(1).getName());
    }
}
