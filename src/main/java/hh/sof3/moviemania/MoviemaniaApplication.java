package hh.sof3.moviemania;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MoviemaniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviemaniaApplication.class, args);
	}
}
