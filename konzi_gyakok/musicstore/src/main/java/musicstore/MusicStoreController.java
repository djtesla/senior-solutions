package musicstore;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
//Alapértelmezetten a /api/instruments URL-en várjuk a kéréseket
@RequestMapping("/api/instruments")
public class MusicStoreController {

    private MusicStoreService musicStoreService;

    public MusicStoreController(MusicStoreService musicStoreService) {
        this.musicStoreService = musicStoreService;
    }


    //Az alapértelmezett URL-en lehessen az összes hangszert lekérdezni. Itt opcionálisan lehessen márkát és/vagy árat
    // megadni. Ilyenkor csak a lekérdezett márkájú, vagy árú vagy a kérésnek megfelelően mindkét tulajdonsággal rendelkező elemek jelenjenek meg.
    @GetMapping
    public List<InstrumentDTO> listEmployees(@RequestParam Optional<String> brand, @RequestParam Optional<Integer> price) {
        return musicStoreService.listEmployees(brand, price);
    }

    //Az alapértelmezett URL-en keresztül lehessen új hangszert felvenni. Ekkor csak a márkát, típust és árat várjuk,
    // a dátumot az aznapi dátumra állítsuk be.
    @PostMapping
    public InstrumentDTO createInstrument(@RequestBody @Valid CreateInstrumentCommand command) {
        return musicStoreService.createInstrument(command);
    }

    //Az alapértelmezett URL-en keresztül lehessen törölni az összes hangszert.
    @DeleteMapping
    public void deleteAllInstruments() {
        musicStoreService.deleteAllInstruments();
    }


    //Az /{id} URL-en keresztül lehessen lekérdezni egy hangszert.
    @GetMapping("/{id}")
    public InstrumentDTO listInstrumentById(@PathVariable("id") long id) {
        return musicStoreService.listInstrumentById(id);
    }

    //Az /{id} URL-en keresztül lehessen frissíteni az árat. Ha az ár ugyanaz mint amit már tárolunk,
    // akkor ne történjen semmi, ha az ár más, akkor az árat és a dátumot is frissítsük!
    @PutMapping("/{id}")
    public InstrumentDTO updatePrice(@PathVariable("id") long id, @RequestBody @Valid UpdatePriceCommand command) {
        return musicStoreService.updatePrice(id, command);
    }

    //Az /{id} URL-en keresztül lehessen törölni az aktuális elemet.
    @DeleteMapping("/{id}")
    public void deleteInstrumentById(@PathVariable("id") long id) {
        musicStoreService.deleteInstrumentById(id);
    }


    //Figyeljünk arra, hogyha nem megfelelő id-t kapunk, akkor 404, not found státusszal térjünk vissza.
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae) {
        Problem problem = Problem.builder()
                .withStatus(Status.NOT_FOUND)
                .withTitle("Not found")
                .withDetail(iae.getMessage())
                .withType(URI.create("instruments/not-found"))
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problem);
    }


    //Ne lehessen létrehozni elemet meg nem adott márkával és negatív árral.
    //Ne lehessen frissíteni az árat negatív árral.
    //Figyeljünk, hogy a tesztnek megfelelő kritériumokat teljesítsük (url, státusz-kód, stb.)


}
