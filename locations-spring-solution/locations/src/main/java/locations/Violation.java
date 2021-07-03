package locations;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@NotBlank
@AllArgsConstructor
public class Violation {

    private String field;
    private String message;
}
