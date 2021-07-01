package locations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DistanceService {

    LocationsRepository locationsRepository = new LocationsRepository();


    public Optional<Double> calculateDistance(String name1, String name2) {
        if (locationsRepository.findByName(name1).equals(Optional.empty()) || locationsRepository.findByName(name2).equals(Optional.empty())) {
            return Optional.empty();
        } else {
            Location location1 = locationsRepository.findByName(name1).get();
            Location location2 = locationsRepository.findByName(name2).get();
            return Optional.of(location1.distanceFrom(location2));
        }

    }
}
