package locations;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LocationOperatorsTest {

    /*
    Budapest,47.497912,19.040235
    Aberdeen,57.9,2.9
    Bremen,535.5,8.49
    Dakar,14.40,17.28
    Naples,40.50,14.15
    Greenwich,51.477928,0
    Null Island,0,0 */

    private LocationOperators locationOperators;
    private LocationParser locationParser;

    @BeforeEach
    void init(){
        locationOperators = new LocationOperators();
        locationParser = new LocationParser();
    }

    @Test
    void testFilterOnNorth () {
        List<Location> locations = List.of(locationParser.parse("Bremen,535.5,8.49"),
                locationParser.parse("Dakar,-14.40,17.28"),
                locationParser.parse("Null Island,-1,0")
                );
        List<Location> locationsOnNorth = locationOperators.filterOnNorth(locations);
        assertThat(locationsOnNorth).hasSize(2).extracting(Location::getName).containsOnly("Dakar", "Null Island");


    }
}
