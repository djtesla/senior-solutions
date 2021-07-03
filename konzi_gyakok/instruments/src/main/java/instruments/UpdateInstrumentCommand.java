package instruments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInstrumentCommand {


    @Positive(message = "price must be positive")
    private int price;

}
