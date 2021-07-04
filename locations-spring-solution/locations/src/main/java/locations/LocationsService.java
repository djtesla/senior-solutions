package locations;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@Service
public class LocationsService {

    private AtomicLong idGenerator = new AtomicLong();

    private ModelMapper modelMapper;

    private List<Location> locations = Arrays.asList(new Location(1, "Bécs", 23.5, 12.2),
            new Location(2, "Budapest", 123.9, 112.5),
            new Location(3, "Jakarta", 33.7, 142.1));

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<LocationDto> getLocations(Optional<String> name, Optional<Double> minLat, Optional<Double> mintLon) {
        return locations.stream()
                .filter(location -> name.isEmpty() || location.getName().equalsIgnoreCase(name.get()))
                .filter(location -> minLat.isEmpty() || location.getLat() > minLat.get())
                .filter(location -> mintLon.isEmpty() || location.getLon() > mintLon.get())
                .map(location -> modelMapper.map(location, LocationDto.class)).collect(Collectors.toList());
    }

    public LocationDto getLocationById(long id) {
        Location locationRequested = findLocationById(id);
        return modelMapper.map(locationRequested, LocationDto.class);
    }

    private Location findLocationById(long id) {
        return locations.stream().filter(location -> location.getId() == id)
                .findAny().orElseThrow(()->new LocationNotFoundException("Cannot find location by id"));
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(idGenerator.incrementAndGet(), command.getName(), command.getLat(), command.getLon());
        locations.add(location);
        return modelMapper.map(location, LocationDto.class);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location locationToBeUpdated = findLocationById(id);
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
        Location locationToBeDeleted = findLocationById(id);
        locations.remove(locationToBeDeleted);
    }

    public void deleteAllLocations() {
        locations = new ArrayList<>();
        idGenerator = new AtomicLong();
    }
}


