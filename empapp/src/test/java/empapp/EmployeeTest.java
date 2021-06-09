package empapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    @Test
    void getAgeTest(){
        //Given

        Employee employee = new Employee("John Doe",1980);

        //When
        int age = employee.getAge(2021);

        //Then
        assertEquals(41, age);
    }

    @Test
    void getAgeTestInOneRow(){

        assertEquals(41, new Employee("John Doe",1980).getAge(2021));
    }
}
