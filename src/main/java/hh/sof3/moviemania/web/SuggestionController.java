package hh.sof3.moviemania.web;

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
import hh.sof3.moviemania.domain.Suggestion;
import hh.sof3.moviemania.domain.SuggestionRepository;

@Controller
public class SuggestionController {

    @Autowired
    private SuggestionRepository srepo;

    @Autowired
    private GenreRepository grepo;

    @GetMapping("/suggestionlist")
    public String showSuggestions(Model model) {
        model.addAttribute("suggestions", srepo.findAll());
        return "suggestionlist";
    }

    @GetMapping("/makesuggestion")
    public String makeSuggestion(Model model) {
        model.addAttribute("suggestion", new Suggestion());
        return "makesuggestion";
    }

    @PostMapping("/savesuggestion")
    public String saveSuggestion(@ModelAttribute("suggestion") Suggestion suggestion) {
        System.out.println("Received suggestion: " + suggestion);
        srepo.save(suggestion);
        return "redirect:/movielist";
    }

    @GetMapping("/suggestion/{id}/watched")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String markAsWatched(@PathVariable("id") long id, Model model) {
        Suggestion suggestion = srepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid suggestion Id"));
        suggestion.setStatus("Watched");
        srepo.save(suggestion);

        model.addAttribute("movie", new Movie());
        model.addAttribute("genre", grepo.findAll());
        return "redirect:/addmovie";
    }
}
