package movieservice;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class MovieService {


    private AtomicLong idGenerator = new AtomicLong();
    private ModelMapper modelMapper;
    private List<Movie> movies = new ArrayList<>();


    public MovieDto listMovies() {
        List<Movie> moviesWithDataToDisplay = movies.stream().map(movie -> new Movie(movie.getName(), movie.getLengthInMin(), movie.getAverageOfRatings())).collect(Collectors.toList());
        Type targetListType = new TypeToken<List<MovieDto>>() {
        }.getType();
        return modelMapper.map(moviesWithDataToDisplay, targetListType);
    }

    public MovieDto listMoviesAsPerPrefix(Optional<String> prefix) {
        List<Movie> moviesWithDataToDisplay = movies.stream().map(movie -> new Movie(movie.getName(), movie.getLengthInMin(), movie.getAverageOfRatings()))
                .filter(movie -> prefix.isEmpty() || movie.getName().toLowerCase().equals(prefix.get().toLowerCase()))
                .collect(Collectors.toList());
        Type targetListType = new TypeToken<List<MovieDto>>() {
        }.getType();
        return modelMapper.map(moviesWithDataToDisplay, targetListType);
    }

    public MovieDto listMoviesById(long id) {
        Movie movieToDisplay = movies.stream().map(movie -> new Movie(movie.getName(), movie.getLengthInMin(), movie.getAverageOfRatings()))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Cannot fid Movie by id: " + id));
        return modelMapper.map(movieToDisplay, MovieDto.class);
    }

    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movieToCreate = new Movie(idGenerator.incrementAndGet(), command.getName(), command.getLengthInMin());
        movies.add(movieToCreate);
        return modelMapper.map(movieToCreate, MovieDto.class);
    }

    public MovieDto listAverageRatingsById(long id) {
        Movie movieToDisplay = movies.stream().filter(movie -> movie.getId() == id).findAny().orElseThrow(() -> new IllegalArgumentException("Cannot find movie by id: " + id));
        return modelMapper.map(movieToDisplay, MovieDto.class);
    }

    public void addRatingById(long id, RatingCommand command) {
        double rate = command.getRate();
        Movie movieToBeRated = movies.stream().filter(movie -> movie.getId() == id).findAny().orElseThrow(() -> new IllegalArgumentException("Cannot find movie by id: " + id));
        movieToBeRated.addRate(rate);
        double average = movieToBeRated.getRatings().stream().collect(Collectors.summarizingDouble(r -> r.doubleValue())).getAverage();
        movieToBeRated.setAverageOfRatings(average);

    }

}

