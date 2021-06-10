package empapp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class EmployeeTest {

    Employee employee;

   /* public EmployeeTest() {
        System.out.println("KONSTRUKTOR");
    }*/

    @BeforeEach
    void init() {
        //System.out.println("Init");
        employee = new Employee("John Doe", 1980);
    }

    @Test
        //@DisplayName("GET AGE TEST")
    void get_Age_Test1() {
        //Given
        //When
        int age = employee.getAge(2021);
        //Then
        //assertEquals(41, age);

        //System.out.println("TC1");
    }

    @Test
    void getAgeTest2() {
        int age = employee.getAge(2011);
        assumeTrue(employee == null);
        assertEquals(31, age);

        //System.out.println("TC2");
    }

    @Test
    void testEquality() {
        Employee employee2 = new Employee("John Doe", 1980);
        ;
        assertEquals(employee, employee2);
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void getAgeTestInOneRow() {
        //fail();
        assertEquals(41, new Employee("John Doe", 1980).getAge(2021));

        //System.out.println("TC3");
    }

    @Test
    void testAgeAndName() {
        assumeTrue(employee != null);
        assertAll(
                () -> assertEquals("John Doe", employee.getName()),
                () -> assertEquals(41, employee.getAge(2021))
        );
    }


    @Test
    void testCreateEmployeeWithYearOfBirth1969() {
        //assertThrows(IllegalArgumentException.class, ()-> new Employee("Géza", 1969));
        IllegalArgumentException ie = assertThrows(IllegalArgumentException.class, () -> new Employee("Géza", 1969));
        assertEquals("Guy is too old.", ie.getMessage());
    }
}
