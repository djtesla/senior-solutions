package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

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
        when(locationsService.getLocations(Optional.empty(), Optional.empty(), Optional.empty())).thenReturn(Arrays.asList(
                new LocationDto(1, "Bécs", 23.5, 12.2),
                new LocationDto(2, "Budapest", 123.9, 112.5),
                new LocationDto(3, "Jakarta", 33.7, 142.1)));
        assertThat(locationsController.getLocations(Optional.empty(), Optional.empty(), Optional.empty())).extracting(LocationDto::getName).contains("Bécs");
    }



}