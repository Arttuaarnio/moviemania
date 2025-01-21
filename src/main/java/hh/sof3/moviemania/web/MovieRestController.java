package hh.sof3.moviemania.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hh.sof3.moviemania.domain.AppUser;
import hh.sof3.moviemania.domain.Movie;
import hh.sof3.moviemania.domain.MovieRepository;
import hh.sof3.moviemania.domain.AppUserRepository;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {

    @Autowired
    private MovieRepository mrepo;

    @Autowired
    private AppUserRepository urepo;

    // Add a movie to a user's favorites
    @PostMapping("/users/{userId}/favorites")
    public ResponseEntity<String> addFavoriteMovie(@PathVariable Long userId, @RequestBody Movie movie) {
        AppUser user = urepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Save the movie if it doesn't exist
        if (!mrepo.existsByTitleAndReleaseyear(movie.getTitle(), movie.getReleaseYear())) {
            mrepo.save(movie);
        }

        // Find the saved movie
        Movie savedMovie = mrepo.findByTitleAndReleaseyear(movie.getTitle(), movie.getReleaseYear())
                .orElseThrow(() -> new RuntimeException("Failed to save or find the movie"));
                
        // Add the movie to user's favorites
        user.getFavoriteMovies().add(savedMovie);
        urepo.save(user);

        return ResponseEntity.ok("Movie added to user's favorites");
    }

    // List all favorite movies for a user
    @GetMapping("/users/{userId}/favorites")
    public ResponseEntity<List<Movie>> getUserFavorites(@PathVariable Long userId) {
        AppUser user = urepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        List<Movie> favoriteMovies = user.getFavoriteMovies();
        return ResponseEntity.ok(favoriteMovies);
    }

    // Delete a movie from a user's favorites
    @DeleteMapping("/users/{userId}/favorites/{movieId}")
    public ResponseEntity<String> deleteFavoriteMovie(@PathVariable Long userId, @PathVariable Long movieId) {
        AppUser user = urepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Movie movie = mrepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + movieId));

        // Remove the movie from user's favorites
        if (user.getFavoriteMovies().contains(movie)) {
            user.getFavoriteMovies().remove(movie);
            urepo.save(user);
            return ResponseEntity.ok("Movie removed from favorites");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movie is not in the user's favorites");
        }
    }
}
