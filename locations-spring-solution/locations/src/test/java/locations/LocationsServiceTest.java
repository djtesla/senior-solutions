package locations;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {

    LocationsService locationsService;


    @BeforeEach
    void init() {
        locationsService = new LocationsService();

    }

    @Test
    void testGetLocations() {
        assertThat(locationsService.getLocations())
                .extracting(Location::getName, Location::getId).contains(tuple("Budapest", 2L));

    }

}