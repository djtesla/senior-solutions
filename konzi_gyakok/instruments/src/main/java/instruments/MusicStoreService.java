package instruments;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MusicStoreService {

    private AtomicLong idGenerator = new AtomicLong();

    private List<Instrument> instruments = new ArrayList<>();

    private ModelMapper modelMapper;

    public MusicStoreService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<InstrumentDto> listInstruments(Optional<String> brand, Optional<Integer> price) {
        List<Instrument> instrumentsFiltered = instruments.stream()
                .filter(instrument -> brand.isEmpty() || instrument.getBrand().equals(brand.get()))
                .filter(instrument ->  price.isEmpty() || instrument.getPrice()==price.get()).collect(Collectors.toList());
        Type targetListType = new TypeToken<List<InstrumentDto>>() {
        }.getType();
        return modelMapper.map(instrumentsFiltered, targetListType);

    }

    public InstrumentDto createInstrument(CreateInstrumentCommand command) {
        Instrument instrument = new Instrument(idGenerator.incrementAndGet(),
                command.getBrand(),
                command.getType(),
                command.getPrice(),
                LocalDate.now());
        instruments.add(instrument);
        System.out.println(instrument);
        return modelMapper.map(instrument, InstrumentDto.class);
    }

    public void deleteAllInstruments() {
        instruments = new ArrayList<>();
        idGenerator = new AtomicLong();
    }

    public void uploadPerCSV() {
        instruments = new ArrayList<>();
        idGenerator = new AtomicLong();
        try (BufferedReader reader = Files.newBufferedReader(Path.of("instruments.csv"))) {
            String line;
            while ((line = reader.readLine() )!= null) {
                Instrument instrument = createInstrumentPerLine(line);
                instruments.add(instrument);
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("File not found", ioe);
        }
    }

    private Instrument createInstrumentPerLine(String line) {
        String[] lineData =  line.split(";");
        String brand = lineData[0];
        InstrumentType type = InstrumentType.valueOf(lineData[1]);
        int price = Integer.parseInt(lineData[2]);
        return new Instrument(idGenerator.incrementAndGet(),brand,type,price,LocalDate.now());
    }

    public InstrumentDto findInstrumentById(long id) {
        Instrument instrument =  instruments.stream()
                .filter(i -> i.getId()==id).findAny().orElseThrow(()->new IllegalArgumentException("Instrument cannot find by id"));
        return modelMapper.map(instrument, InstrumentDto.class);
    }

    public InstrumentDto updateInstrument(long id, UpdateInstrumentCommand command) {
        Instrument instrument = instruments.stream().filter(instrument1 -> instrument1.getId()==id).findAny().orElseThrow(()->new IllegalArgumentException("Cannot find instrument by id"));
        if(command.getPrice() == instrument.getPrice()) {
        } else {
            instrument.setPrice(command.getPrice());
            instrument.setDateOfPublish(LocalDate.now());
        }
        return modelMapper.map(instrument, InstrumentDto.class);
    }
}
