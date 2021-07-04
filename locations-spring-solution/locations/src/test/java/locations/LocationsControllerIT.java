package locations;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationsControllerIT {

    @Autowired
    LocationsController locationsController;


    @Test
    void testGetLocations() {
        assertThat(locationsController.getLocations(Optional.empty(), Optional.empty(), Optional.empty())).extracting(LocationDto::getName).contains("Bécs");
    }

}