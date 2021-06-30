package locations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@Service
public class LocationsService {

    private List<Location> locations = Arrays.asList(new Location(1, "Bécs", 23.5, 12.2),
            new Location(2, "Budapest", 123.9, 112.5),
            new Location(3, "Jakarta", 33.7, 142.1));



    public List<Location> getLocations() {
        return new ArrayList<>(locations);
    }
}


