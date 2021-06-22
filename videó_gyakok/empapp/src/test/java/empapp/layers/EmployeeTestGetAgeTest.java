package empapp.layers;

import empapp.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Tag("entity")
public class EmployeeTestGetAgeTest {

    Employee employee;


    @Nested
    class WithAge1970 {
        @BeforeEach
        void init() {
            employee = new Employee("John Doe", 1970);
        }

        @Test
        void testGetAge1970() {
        }

        @Test
        void testGetAge1970Jane() {
        }


    }

    @Nested
    class WithAge1980 {
        @BeforeEach
        void init() {
            employee = new Employee("John Doe", 1980);
        }
    }

    @Test
    void testGetAge1980() {
    }


    @Test
    void testGetAge1980Jane() {
    }


}
