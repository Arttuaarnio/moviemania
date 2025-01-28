package hh.sof3.moviemania.service;

import hh.sof3.moviemania.domain.Movie;
import hh.sof3.moviemania.domain.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Adds a new movie to the database
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Gets all movies from the database
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
