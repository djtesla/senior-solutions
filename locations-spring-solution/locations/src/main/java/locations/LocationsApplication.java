package locations;

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

}
