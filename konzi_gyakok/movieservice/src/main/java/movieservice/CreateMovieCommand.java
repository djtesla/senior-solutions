package movieservice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateMovieCommand {

    private long id;
    private String name;
    private double lengthInMin;

    public CreateMovieCommand(String name, double lengthInMin) {
        this.name = name;
        this.lengthInMin = lengthInMin;
    }
}
