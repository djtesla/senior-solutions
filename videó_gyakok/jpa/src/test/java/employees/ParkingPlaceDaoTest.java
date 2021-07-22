package employees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ParkingPlaceDaoTest {

    private ParkingPlaceDao parkingPlaceDao;
    private EmployeeDao employeeDao;

    @BeforeEach
    void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        parkingPlaceDao = new ParkingPlaceDao(entityManagerFactory);
        employeeDao = new EmployeeDao(entityManagerFactory);
    }

    @Test
    void testSave() {
        ParkingPlace parkingPlace = new ParkingPlace(123);
        parkingPlaceDao.saveParkingPlace(parkingPlace);
        ParkingPlace another = parkingPlaceDao.findParkingPLaceNumber(parkingPlace.getNumber());
        assertEquals(123, another.getNumber());
    }

    @Test
    void testSaveEmployeeWithParkingPLace() {
        ParkingPlace parkingPlace = new ParkingPlace(123);
        parkingPlaceDao.saveParkingPlace(parkingPlace); //először le kell menteni a parkingot, ha nincs (cascade = CascadeType.PERSIST)!
        Employee employee = new Employee("John Doe");
        employee.setParkingPlace(parkingPlace);
        employeeDao.save(employee);
        Employee anotherEmployee = employeeDao.findById(employee.getId());
        assertEquals(123, anotherEmployee.getParkingPlace().getNumber());




    }
}
