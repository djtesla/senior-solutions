package instruments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentDto {

    private long id;


    private String brand;
    private InstrumentType type;
    private int price;
    private LocalDate dateOfPublish;
}
