package bikeservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BikeController {

    private BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping("/history")
    public List<Bike> getBikes(){
    return bikeService.getBikes();
    }

    @GetMapping("/users")
    public List<String> getUsers(){
        return bikeService.getUsers();
    }
}
