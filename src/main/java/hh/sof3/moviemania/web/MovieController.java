package hh.sof3.moviemania.web;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.sof3.moviemania.domain.GenreRepository;
import hh.sof3.moviemania.domain.Movie;
import hh.sof3.moviemania.domain.MovieRepository;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository mrepo;

    @Autowired
    private GenreRepository grepo;

    @GetMapping("movielist")
    public String showMovies(Model model) {
        model.addAttribute("movies", mrepo.findAll());
        return "movielist";
    }

    @GetMapping("/addmovie")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addMovie(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("genre", grepo.findAll());
        return "addmovie";
    }

    @PostMapping("/savemovie")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveMovie(@ModelAttribute("movie") Movie movie) {
        mrepo.save(movie);
        return "redirect:/movielist";
    }

    @GetMapping("delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteMovie(@PathVariable("id") Long id, Model model) {
        mrepo.deleteById(id);
        return "redirect:/movielist";
    }

    @GetMapping("edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editMovie(@PathVariable("id") Long id, Model model) {
        Movie movie = mrepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie Id"));
        model.addAttribute("movie", movie);
        model.addAttribute("genres", grepo.findAll());
        return "editmovie";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateMovie(@ModelAttribute Movie movie) {
        mrepo.save(movie);
        return "redirect:/movielist";
    }

    @GetMapping("/movierandomizer")
    public String getRandomMovie(Model model) {
        List<Movie> movies = (List<Movie>) mrepo.findAll();

        if (!movies.isEmpty()) {
            Random random = new Random();
            Movie randomMovie = movies.get(random.nextInt(movies.size()));
            model.addAttribute("movie", randomMovie);
        }
        return "randommovie";
    }
}
