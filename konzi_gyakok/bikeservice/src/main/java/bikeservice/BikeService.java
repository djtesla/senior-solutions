package bikeservice;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BikeService {

    private List<Bike> bikes = new ArrayList<>();

    public List<Bike> getBikes() {
        if (bikes.size()==0) {
            bikes = readFromFile("bikeservice.csv");
        }
        return bikes;
    }

    private List<Bike> readFromFile(String fileName) {
        List<Bike> bikesRead = new ArrayList<>();
        try(BufferedReader reader = Files.newBufferedReader(Path.of(fileName))) {
            String line;
            while ((line = reader.readLine())!= null) {
                bikesRead.add(getBikePerLine(line));
            }
            return bikesRead;
        } catch (IOException ioe) {
          throw new IllegalStateException("Cannot read file", ioe);
      }
    }

    //FH676;US3a34;2021-06-25 11:20:42;1.2
    private Bike getBikePerLine(String line) {
        String[] bikeStr = line.split(";");
        String id =bikeStr[0];
        String lastUserId = bikeStr[1];
        String timeOfLastDepositStr = bikeStr[2];
        double distanceOfLasRide = Double.parseDouble(bikeStr[3]);

        LocalDateTime timeOfLastDeposit = parseTimeStr(timeOfLastDepositStr);
    return new Bike(id, lastUserId, timeOfLastDeposit, distanceOfLasRide);
    }

    private LocalDateTime parseTimeStr(String timeOfLastDepositStr) {
        DateTimeFormatter formatter = DateTimeFormatter. ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(timeOfLastDepositStr, formatter);
    }

    public List<String> getUsers() {
        return bikes.stream().map(Bike::getId).collect(Collectors.toList());
    }
}
