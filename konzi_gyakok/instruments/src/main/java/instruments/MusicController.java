package instruments;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/instruments")
public class MusicController {

    MusicStoreService musicStoreService;

    public MusicController(MusicStoreService musicStoreService) {
        this.musicStoreService = musicStoreService;
    }


    @GetMapping
    public List<InstrumentDto> listInstruments(@RequestParam Optional<String> brand, @RequestParam Optional<Integer> price) {
        return musicStoreService.listInstruments(brand, price);
    }


    @GetMapping("/{id}")
    public InstrumentDto findInstrumentById(@PathVariable("id") long id) {
        return musicStoreService.findInstrumentById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstrumentDto createInstrument(@RequestBody @Valid CreateInstrumentCommand command) {
        return musicStoreService.createInstrument(command);
    }

    @PostMapping("/{id}")
    public InstrumentDto updateInstrument(@PathVariable("id") long id,
                                          @Valid @RequestBody UpdateInstrumentCommand command) {
        return musicStoreService.updateInstrument(id, command);
    }

    @PostMapping("/upload")
    public void uploadPerCSV() {
        musicStoreService.uploadPerCSV();
    }

    @DeleteMapping
    public void deleteAllInstruments() {
        musicStoreService.deleteAllInstruments();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae) {
        Problem problem = Problem.builder()
                .withType(URI.create("instruments/invalid-json request"))
                .withTitle(("Not found"))
                .withStatus(Status.NOT_FOUND)
                .withDetail(iae.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problem);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Problem> handleValidException(MethodArgumentNotValidException mae) {

        List<Violation> violations = mae.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new Violation(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        Problem problem = Problem.builder()
                .withType(URI.create("instruments/not valid"))
                .withTitle(("Validation error"))
                .withStatus(Status.BAD_REQUEST)
                .withDetail(mae.getMessage())
                .with("violations", violations)
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problem);
    }

}