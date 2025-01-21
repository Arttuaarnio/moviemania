package hh.sof3.moviemania;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof3.moviemania.domain.AppUser;
import hh.sof3.moviemania.domain.AppUserRepository;
import hh.sof3.moviemania.domain.Movie;
import hh.sof3.moviemania.domain.MovieRepository;


@SpringBootApplication
public class MoviemaniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviemaniaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(MovieRepository mrepo, AppUserRepository urepo) {
		return (args) -> {

			Movie movie1 = new Movie("The Black phone", "Scott Derrickson", 2021, "After being abducted and locked in a basement, a boy starts receiving calls on a disconnected phone from the killer's previous victims.", "scary");
			
			mrepo.save(movie1);

			AppUser user1 = new AppUser("admin","$2a$10$EY4eKImhpDU/sXOYItNTGePABBK1QvZtXYEuFlhHtpt1uC78Qtdi.","ADMIN");

			urepo.save(user1);
		};
	}
}
