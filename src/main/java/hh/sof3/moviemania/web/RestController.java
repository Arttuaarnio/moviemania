package hh.sof3.moviemania.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof3.moviemania.domain.Genre;
import hh.sof3.moviemania.domain.GenreRepository;
import hh.sof3.moviemania.domain.Movie;
import hh.sof3.moviemania.domain.MovieRepository;
import hh.sof3.moviemania.domain.Suggestion;
import hh.sof3.moviemania.domain.SuggestionRepository;

@CrossOrigin
@Controller
public class RestController {

    @Autowired
    private MovieRepository mrepo;

    @Autowired
    private SuggestionRepository srepo;

    @Autowired
    private GenreRepository grepo;

// Simple REST for listing movies, suggestions and genres and finding a movie by id
 @GetMapping("/movies")
 public @ResponseBody List<Movie> movieListRest() {
    return (List<Movie>) mrepo.findAll();
 }

 @GetMapping("/movies/{id}")
 public @ResponseBody Optional<Movie> findMovieRest(@PathVariable("id") Long movieid) {
     return mrepo.findById(movieid);
 }

 @GetMapping("/suggestions")
 public @ResponseBody List<Suggestion> suggestionListRest() {
     return (List<Suggestion>) srepo.findAll();
 }

 @GetMapping("/genres")
 public @ResponseBody List<Genre> genreListRest() {
     return (List<Genre>) grepo.findAll();
 }

}
