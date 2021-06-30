package bikeservice;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BikeControllerTest {

    @Mock
    BikeService bikeService;

    @InjectMocks
    BikeController bikeController;

    @Test
    void testGetBikes () {
        when(bikeService.getBikes()).thenReturn(new ArrayList<>());
        assertThat(bikeController.getBikes()).hasSize(0);
    }

}