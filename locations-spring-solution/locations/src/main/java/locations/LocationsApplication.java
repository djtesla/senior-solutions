package locations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LocationsApplication {

/*	@Bean
	public LocationsService locationsService() {
		return new LocationsService();
	}*/

	public static void main(String[] args) {
		SpringApplication.run(LocationsApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper (){
		return new ModelMapper();
	}
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().findAndRegisterModules();
	}

}
