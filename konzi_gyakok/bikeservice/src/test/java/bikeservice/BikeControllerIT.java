package bikeservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BikeControllerIT {

    @Autowired
    BikeController bikeController;


    @Test
    void testGetBikes(){
        Assertions.assertThat(bikeController.getBikes()).hasSize(5);
    }

}