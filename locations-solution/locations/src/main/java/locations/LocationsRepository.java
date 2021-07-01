package locations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocationsRepository {

    private List<Location> locations = new ArrayList<>();


    public Optional<Location> findByName(String name) {
        try {
            return Optional.of(locations.stream().filter(location -> location.getName().equals(name)).findAny().orElseThrow(() -> new IllegalArgumentException("Cannot find location by name " + name)));
        } catch (IllegalArgumentException iae) {
            return Optional.empty();
        }

    }

}
