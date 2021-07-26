package moviesspringdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Long id;
    private String title;
    private List<Double> ratings;

    public MovieDto(String title, List<Double> ratings) {
        this.title = title;
        this.ratings = ratings;
    }
}

