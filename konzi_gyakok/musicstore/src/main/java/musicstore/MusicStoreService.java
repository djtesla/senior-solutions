package musicstore;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MusicStoreService {

    private ModelMapper modelMapper;
    private List<Instrument> instruments = new ArrayList<>();
    private AtomicLong idGenerator = new AtomicLong();

    public MusicStoreService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<InstrumentDTO> listEmployees(Optional<String> brand, Optional<Integer> price) {
        return instruments.stream().filter(instrument -> brand.isEmpty() || instrument.getBrand().equalsIgnoreCase(brand.get()))
                .filter(instrument -> price.isEmpty() || instrument.getPrice() == price.get())
                .map(instrument -> modelMapper.map(instrument, InstrumentDTO.class)).collect(Collectors.toList());
    }

    public InstrumentDTO createInstrument(CreateInstrumentCommand command) {
        Instrument instrument = new Instrument(idGenerator.incrementAndGet(), command.getBrand(), command.getType(), command.getPrice(), LocalDate.now());
        instruments.add(instrument);
        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    public void deleteAllInstruments() {
        instruments = new ArrayList<>();
        idGenerator = new AtomicLong();
    }

    public InstrumentDTO listInstrumentById(long id) {
        Instrument instrument = findInstrumentById(id);
        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    private Instrument findInstrumentById(long id) {
        return instruments.stream().filter(instrument -> instrument.getId() == id)
                .findAny().orElseThrow(() -> new IllegalArgumentException("Instrument cannot be found by id"));
    }

    public InstrumentDTO updatePrice(long id, UpdatePriceCommand command) {
        Instrument instrument = findInstrumentById(id);
        if (instrument.getPrice()!=command.getPrice()) {
            instrument.setPrice(command.getPrice());
            instrument.setPostDate(LocalDate.now());
        }
        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    public void deleteInstrumentById(long id) {
        Instrument instrument = findInstrumentById(id);
        instruments.remove(instrument);
    }
}
