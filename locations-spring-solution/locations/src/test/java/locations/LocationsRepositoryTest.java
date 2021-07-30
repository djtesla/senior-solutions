package locations;

import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@Sql(statements = "delete from locations")
public class LocationsRepositoryTest {

    @Autowired
    private LocationsRepository locationsRepository;

    @Test
    public void testSaveThenFindById() {
        Location location = new Location("Bécs", 23.5, 12.2);
        locationsRepository.save(location);
        long id = location.getId();
        Optional<Location> another = locationsRepository.findById(id);
        assertThat(another.get().getName()).isEqualTo("Bécs");
    }

    @Test
    public void testFindAll() {
        Location location1 = new Location("Bécs", 23.5, 12.2);
        Location location2 = new Location("Budapest", 123.9, 112.5);
        Location location3 = new Location("Jakarta", 33.7, 142.1);
        locationsRepository.save(location1);
        locationsRepository.save(location2);
        locationsRepository.save(location3);
        List<Location> locations =locationsRepository.findAll();
        assertEquals(3, locations.size());
    }

    @Test
    public void testDeleteById() {
        Location location1 = new Location("Bécs", 23.5, 12.2);
        Location location2 = new Location("Budapest", 123.9, 112.5);
        locationsRepository.save(location1);
        locationsRepository.save(location2);
        long id1 = location1.getId();
        long id2 = location2.getId();
        locationsRepository.deleteById(id1);
        assertEquals(id2, locationsRepository.findAll().get(0).getId());
    }

    @Test
    public void testFindByPrefix() {
        Location location1 = new Location("Bécs", 23.5, 12.2);
        Location location2 = new Location("Budapest", 123.9, 112.5);
        Location location3 = new Location("Jakarta", 33.7, 142.1);
        locationsRepository.save(location1);
        locationsRepository.save(location2);
        locationsRepository.save(location3);
        List<Location> locations =locationsRepository.findAllByPrefix("bud");
        assertThat(locations).extracting(Location::getName).containsExactly("Budapest");
    }
}
