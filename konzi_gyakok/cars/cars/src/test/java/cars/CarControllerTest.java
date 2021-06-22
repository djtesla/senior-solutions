package cars;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    public static final List<KmState> KM_STATE_1 = Arrays.asList(new KmState(LocalDate.of(2015, 10, 10), 35000),
            new KmState(LocalDate.of(2015, 10, 10), 75000) ,
            new KmState(LocalDate.of(2021, 11, 1), 135000));

    public static final List<KmState> KM_STATE_3 = Arrays.asList(new KmState(LocalDate.of(2019, 10, 10), 15000),
            new KmState(LocalDate.of(2021, 11, 1), 25000));

    public static final List<KmState> KM_STATE_2 = Arrays.asList(new KmState(LocalDate.of(2018, 10, 10), 25000),
            new KmState(LocalDate.of(2020, 10, 10), 50000) ,
            new KmState(LocalDate.of(2021, 11, 1), 82000));

    List<Car> cars = Arrays.asList(new Car("Opel", "Astra", 10, State.BAD,KM_STATE_1),
            new Car("Opel", "Insignia", 6, State.NORMAL,KM_STATE_2),
            new Car("Opel", "CrossLand", 2, State.PERFECT,KM_STATE_3));

    @Mock
    CarService carService;

    @InjectMocks
    CarController carController;


    @Test
    void testGetCars() {

        when(carService.getCars()).thenReturn(cars);
        assertThat(carController.getCars()).hasSize(3).extracting(Car::getAge).contains(6, 2);
    }

    @Test
    void testListBrands() {
        when(carService.listBrands()).thenReturn(List.of("Opel", "Opel", "Opel"));
        assertThat(carController.getBrands()).contains("Opel");
        verify(carService).listBrands();
    }


}
