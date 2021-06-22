package cars;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarControllerIT {

    @Autowired
    CarController carController;

    @Test
    void testGetCars() {
        assertThat(carController.getCars()).hasSize(3).extracting(Car::getAge).contains(6, 2);
    }


    @Test
    void testListBrands() {
        assertThat(carController.getBrands()).containsOnly("Opel");
    }

}