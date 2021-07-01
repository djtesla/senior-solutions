package locations;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class DistanceServiceTest {


    @Mock
    LocationsRepository locationsRepository;

    @InjectMocks
    DistanceService distanceService;

    @Test
    void calculateDistanceTest() {
        when(locationsRepository.findByName(anyString())).thenReturn(Optional.empty());
        assertThat(distanceService.calculateDistance("Bremen","Greenwich").equals(Optional.empty()));

        verify(locationsRepository).findByName("Bremen");
    }

}