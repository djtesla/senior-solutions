package moviesspringdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class MovieService {

    private MoviesRepository moviesRepository;

    private ModelMapper modelMapper;



    public List<MovieDto> getMovies(){
        List<Movie> movies = moviesRepository.findAll();
        return movies.stream().map(movie -> modelMapper.map(movie, MovieDto.class)).collect(Collectors.toList());
    }

    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(command.getTitle());
        moviesRepository.save(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    @Transactional
    public MovieDto addRating(CreateRatingCommand command){
        Movie movie = moviesRepository.findById(command.getId()).orElseThrow(()-> new IllegalArgumentException("Movie cannot find by id"));
        movie.addRating(command.getRating());
        return modelMapper.map(movie, MovieDto.class);


    }
}
