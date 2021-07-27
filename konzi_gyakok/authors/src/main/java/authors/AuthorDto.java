package authors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {

    private Long id;
    private String name;
    private List<BookDto> books;

    public AuthorDto(String name) {
        this.name = name;
    }
}
