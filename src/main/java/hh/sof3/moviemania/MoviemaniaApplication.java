package hh.sof3.moviemania;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof3.moviemania.domain.AppUser;
import hh.sof3.moviemania.domain.AppUserRepository;
import hh.sof3.moviemania.domain.Genre;
import hh.sof3.moviemania.domain.GenreRepository;
import hh.sof3.moviemania.domain.Movie;
import hh.sof3.moviemania.domain.MovieRepository;
import hh.sof3.moviemania.domain.Suggestion;
import hh.sof3.moviemania.domain.SuggestionRepository;

@SpringBootApplication
public class MoviemaniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviemaniaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(MovieRepository mrepo, GenreRepository grepo, SuggestionRepository srepo, AppUserRepository urepo) {
		return (args) -> {
			// Create some genres
			Genre genre1 = new Genre("Action");
			Genre genre2 = new Genre("Comedy");
			Genre genre3 = new Genre("Horror");
			Genre genre4 = new Genre("Romance");
			Genre genre5 = new Genre("Drama");
			Genre genre6 = new Genre("Fantasy");
			Genre genre7 = new Genre("Scifi");
			Genre genre8 = new Genre("Thriller");
			Genre genre9 = new Genre("Crime");
			Genre genre10 = new Genre("Historical");
			Genre genre11 = new Genre("Adventure");
			Genre genre12 = new Genre("Animation");
			Genre genre13 = new Genre("War");
			Genre genre14 = new Genre("Mystery");

			grepo.save(genre1);
			grepo.save(genre2);
			grepo.save(genre3);
			grepo.save(genre4);
			grepo.save(genre5);
			grepo.save(genre6);
			grepo.save(genre7);
			grepo.save(genre8);
			grepo.save(genre9);
			grepo.save(genre10);
			grepo.save(genre11);
			grepo.save(genre12);
			grepo.save(genre13);
			grepo.save(genre14);

			Movie movie1 = new Movie("The Black phone", "Scott Derrickson", 2021, "After being abducted and locked in a basement, a boy starts receiving calls on a disconnected phone from the killer's previous victims.", "scary", Set.of(genre3, genre14, genre8));
			
			mrepo.save(movie1);

			Suggestion suggestion = new Suggestion("The Joker (2021)", null);
			srepo.save(suggestion);

			AppUser user1 = new AppUser("admin","$2a$10$EY4eKImhpDU/sXOYItNTGePABBK1QvZtXYEuFlhHtpt1uC78Qtdi.","ADMIN");

			urepo.save(user1);
		};
	}
}
