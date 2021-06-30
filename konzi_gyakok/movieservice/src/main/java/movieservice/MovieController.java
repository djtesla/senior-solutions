package movieservice;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping
    public MovieDto listMovies () {
        return movieService.listMovies();

    }

    @GetMapping
    public MovieDto listMoviesAsPerPrefix (@RequestParam Optional<String> prefix) {
        return movieService.listMoviesAsPerPrefix(prefix);

    }

    @GetMapping("/{id}")
    public MovieDto listMoviesById(@PathVariable ("id") long id) {
        return movieService.listMoviesById(id);

    }

    @GetMapping("/{id}/rating")
    public double listAverageRatingsById(@PathVariable ("id") long id) {
        return movieService.listAverageRatingsById(id).getAverageOfRatings();

    }

    @PostMapping
    public MovieDto createEmployee(@RequestBody CreateMovieCommand command) {
        return movieService.createMovie(command);
    }

    @PostMapping("/{id}/rating")
    public void addRatingById(@PathVariable ("id") long id, @RequestBody RatingCommand command) {
        movieService.addRatingById(id, command);

    }

}
