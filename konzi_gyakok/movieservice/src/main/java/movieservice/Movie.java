package movieservice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Movie {
    private long id;
    private String name;
    private double lengthInMin;
    private List<Double> ratings;
    private double averageOfRatings;

    public Movie(String name, double lengthInMin) {
        this.name = name;
        this.lengthInMin = lengthInMin;
    }

    public Movie(String name, double lengthInMin, double averageOfRatings) {
        this.name = name;
        this.lengthInMin = lengthInMin;
        this.averageOfRatings = averageOfRatings;
    }

    public Movie(long id, String name, double lengthInMin) {
        this.id = id;
        this.name = name;
        this.lengthInMin = lengthInMin;
    }

    public void addRate(double rate) {
        ratings.add(rate);
    }
}
