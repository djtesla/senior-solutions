package cars;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    private CarService carService;


    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<Car> getCars () {
        return carService.getCars();
    }

    @GetMapping("/brands")
    public List<String> getBrands() {
        return carService.listBrands();
    }


}
