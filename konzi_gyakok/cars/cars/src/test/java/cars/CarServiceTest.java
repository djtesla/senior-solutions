package cars;


import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    CarService carService;

    @BeforeEach
    void init() {
        carService = new CarService();
    }

    @Test
    void testGetCars() {
        assertThat(carService.getCars()).hasSize(3).extracting(Car::getAge).contains(6, 2);
    }


    @Test
    void testListBrands() {
        assertThat(carService.listBrands()).containsOnly("Opel");


    }

}