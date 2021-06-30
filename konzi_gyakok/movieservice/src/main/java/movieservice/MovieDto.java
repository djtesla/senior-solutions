package movieservice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MovieDto {


    private long id;
    private String name;
    private int lengthInMin;
    private List<Double> ratings;
    private double averageOfRatings;

    public MovieDto(String name, int lengthInMin) {
        this.name = name;
        this.lengthInMin = lengthInMin;
    }

    public MovieDto(String name, int lengthInMin, double averageOfRatings) {
        this.name = name;
        this.lengthInMin = lengthInMin;
        this.averageOfRatings = averageOfRatings;
    }
}
