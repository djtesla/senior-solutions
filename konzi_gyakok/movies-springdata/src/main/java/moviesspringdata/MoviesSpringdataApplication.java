package moviesspringdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MoviesSpringdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviesSpringdataApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper (){
        return new ModelMapper();
    }



}
