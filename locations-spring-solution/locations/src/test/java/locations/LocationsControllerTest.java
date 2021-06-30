package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {


    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController;

    @Test
    void testGetLocations() {
        when(locationsService.getLocations()).thenReturn(Arrays.asList(new Location(1, "Bécs", 23.5, 12.2),
                new Location(2, "Budapest", 123.9, 112.5),
                new Location(3, "Jakarta", 33.7, 142.1)));
        assertThat(locationsController.getLocations()).contains("Bécs");
    }



}