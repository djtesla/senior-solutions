package cars;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    public static final List<KmState> KM_STATE_1 = Arrays.asList(new KmState(LocalDate.of(2015, 10, 10), 35000),
            new KmState(LocalDate.of(2015, 10, 10), 75000) ,
            new KmState(LocalDate.of(2021, 11, 1), 135000));

    public static final List<KmState> KM_STATE_3 = Arrays.asList(new KmState(LocalDate.of(2019, 10, 10), 15000),
            new KmState(LocalDate.of(2021, 11, 1), 25000));

    public static final List<KmState> KM_STATE_2 = Arrays.asList(new KmState(LocalDate.of(2018, 10, 10), 25000),
            new KmState(LocalDate.of(2020, 10, 10), 50000) ,
            new KmState(LocalDate.of(2021, 11, 1), 82000));

    private List<Car> cars = Arrays.asList(new Car("Opel", "Astra", 10, State.BAD,KM_STATE_1),
            new Car("Opel", "Insignia", 6, State.NORMAL,KM_STATE_2),
            new Car("Opel", "CrossLand", 2, State.PERFECT,KM_STATE_3));



    public List<Car> getCars() {
        return cars;
    }

    public List<String> listBrands() {
        return cars.stream().map(Car::getBrand).collect(Collectors.toList());
    }
}
