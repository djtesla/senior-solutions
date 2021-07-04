package locations;

import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationsControllerRestTestIT {

    @Autowired
    TestRestTemplate template;

    @BeforeEach
    void init() {
        template.delete("/locations");
    }

    @Test
    void testCreateLocation() {
        LocationDto location = template.postForObject("/locations", new CreateLocationCommand("Neverland", 80.5, 120.11), LocationDto.class);
        assertAll(
                () -> assertEquals("Neverland", location.getName()),
                () -> assertEquals(80.5, location.getLat()),
                () -> assertEquals(120.11, location.getLon())
        );
    }

    @Test
    void testUpdateLocationWithName() {
        template.postForObject("/locations", new CreateLocationCommand("Neverland", 80.5, 120.11), LocationDto.class);
        template.put("/locations/1", new UpdateLocationCommand("ForeverLand"), LocationDto.class);
        LocationDto location = template.getForObject("/locations/1",LocationDto.class);
        assertEquals("ForeverLand", location.getName());

    }

    @Test
    void testUpdateLocationWithNameAndLatAndLon() {
        template.postForObject("/locations", new CreateLocationCommand("Neverland", 80.5, 120.11), LocationDto.class);
        template.put("/locations/1", new UpdateLocationCommand("ForeverLand", 70.1, 100.11), LocationDto.class);
        LocationDto location = template.getForObject("/locations/1",LocationDto.class);
        assertAll(
                () -> assertEquals("ForeverLand", location.getName()),
                () -> assertEquals(70.1, location.getLat()),
                () -> assertEquals(100.11, location.getLon())
        );

    }

    @Test
    void testUpdateLocationWithdLatAndLon() {

    }


    @Test
    void testHandleNotFound() {
        Problem problem = template.getForObject("/locations/1", Problem.class);
        assertEquals(Status.NOT_FOUND,problem.getStatus());
    }


    @Test
    void testHandleNotValid() {
        Problem problem  = template.postForObject("/locations",  new CreateLocationCommand("Neverland", 80.5, 200.11), Problem.class);
        assertEquals(Status.BAD_REQUEST,problem.getStatus());

    }
}
