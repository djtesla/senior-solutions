package bikeservice;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BikeServiceTest {

    BikeService bikeService = new BikeService();

    @Test
    void testGetBikes(){
        List<Bike> bikes = bikeService.getBikes();

        DateTimeFormatter formatter =  DateTimeFormatter. ofPattern("yyyy-MM-dd HH:mm:ss");
        assertThat(bikes).hasSize(5)
                .extracting(Bike::getId, Bike::getTimeOfLastDeposit).contains(tuple("FH636", LocalDateTime.parse("2021-06-23 09:36:12", formatter)));
    }
}