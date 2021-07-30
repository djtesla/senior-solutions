package locations;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class LocationsService {

    //private AtomicLong idGenerator = new AtomicLong();

    private LocationsRepository locationsRepository;

    private ModelMapper modelMapper;

   // private List<Location> locations = Arrays.asList(new Location(1, "Bécs", 23.5, 12.2),
     //       new Location(2, "Budapest", 123.9, 112.5),
       //     new Location(3, "Jakarta", 33.7, 142.1));



    public List<LocationDto> getLocations(Optional<String> name, Optional<Double> minLat, Optional<Double> mintLon) {
        List<Location> locations = locationsRepository.findAll();
        return locations.stream()
                .filter(location -> name.isEmpty() || location.getName().equalsIgnoreCase(name.get()))
                .filter(location -> minLat.isEmpty() || location.getLat() > minLat.get())
                .filter(location -> mintLon.isEmpty() || location.getLon() > mintLon.get())
                .map(location -> modelMapper.map(location, LocationDto.class)).collect(Collectors.toList());
    }

    public LocationDto getLocationById(long id) {
        Location locationRequested = locationsRepository.findById(id).orElseThrow(()->new LocationNotFoundException("Cannot find location by id"));
        return modelMapper.map(locationRequested, LocationDto.class);
    }

    private Location findLocationById(long id) {
        List<Location> locations = locationsRepository.findAll();
        return locations.stream().filter(location -> location.getId() == id)
                .findAny().orElseThrow(()->new LocationNotFoundException("Cannot find location by id"));
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(command.getName(), command.getLat(), command.getLon());
        locationsRepository.save(location);
        return modelMapper.map(location, LocationDto.class);
    }

    @Transactional
    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location locationToBeUpdated = locationsRepository.findById(id).orElseThrow(()->new LocationNotFoundException("Cannot find location by id"));
        if (command.getName()!= null && !locationToBeUpdated.getName().equals(command.getName())) {
            locationToBeUpdated.setName(command.getName());
        }
        if (command.getLon()!= 0 && locationToBeUpdated.getLon()!= command.getLon()) {
            locationToBeUpdated.setLon(command.getLon());
        }
        if (command.getLat()!= 0 && locationToBeUpdated.getLat()!= command.getLat()) {
            locationToBeUpdated.setLat(command.getLat());
        }
        return modelMapper.map(locationToBeUpdated, LocationDto.class);
    }

    public void deleteLocation(long id) {
        locationsRepository.deleteById(id);
    }

    public void deleteAllLocations() {
        locationsRepository.deleteAll();
    }
}


