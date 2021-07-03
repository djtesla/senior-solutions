package instruments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreateInstrumentCommand {


    @NotBlank(message = "Brand name cannot be blank")
    private String brand;
    private InstrumentType type;
    @Positive(message = "price must be positive")
    private int price;


}
