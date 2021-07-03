package locations;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
public class LocationsController {

    private LocationsService locationsService;


    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping("/locations")
    public List<LocationDto> getLocations(@RequestParam Optional<String> name, @RequestParam Optional<Double> minLat, @RequestParam Optional<Double> mintLon) {
        return locationsService.getLocations(name, minLat, mintLon);
    }

    @GetMapping("/{id}")
    public LocationDto getLocationById(@PathVariable("id") long id) {
        return locationsService.getLocationById(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    public LocationDto createLocation(@RequestBody CreateLocationCommand command) {
        return locationsService.createLocation(command);
    }

    @PutMapping("/{id}")
    public LocationDto updateLocation(@PathVariable ("id") long id, @RequestBody UpdateLocationCommand command) {
        return locationsService.updateLocation(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable ("id") long id) {
        locationsService.deleteLocation(id);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<Problem> handleLocationNotFound(LocationNotFoundException lnfe){
        Problem problem = Problem.builder()
                .withType(URI.create("locations/invalid-json request"))
                .withTitle("not found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(lnfe.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> handleNotValid(MethodArgumentNotValidException mae){

        List<Violation> violations = mae.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new Violation(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        Problem problem = Problem.builder()
                .withType(URI.create("locations/invalid-json request"))
                .withTitle("validation error")
                .withStatus(Status.BAD_REQUEST)
                .withDetail(mae.getMessage())
                .with("violations", violations)
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problem);
    }




}