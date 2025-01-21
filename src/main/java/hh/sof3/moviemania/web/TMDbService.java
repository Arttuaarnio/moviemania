package hh.sof3.moviemania.web;

import hh.sof3.moviemania.domain.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

@Service
public class TMDbService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.base.url}")
    private String baseUrl;

    private final WebClient webClient;

    public TMDbService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    // Search for movies by query
    public Optional<Movie> searchMovies(String query) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/search/movie")
                            .queryParam("query", query)
                            .queryParam("api_key", apiKey)
                            .build())
                    .retrieve()
                    .bodyToFlux(Movie.class)
                    .toStream()
                    .findFirst();
        } catch (WebClientResponseException ex) {
            System.err.println("Error fetching movies: " + ex.getMessage());
            return Optional.empty();
        }
    }

    // Get details of a specific movie by ID
    public Optional<Movie> getMovieDetails(Long id) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/movie/{id}")
                            .queryParam("api_key", apiKey)
                            .build(id))
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .blockOptional();
        } catch (WebClientResponseException ex) {
            System.err.println("Error fetching movie details: " + ex.getMessage());
            return Optional.empty();
        }
    }

    // Fetch popular movies (optional enhancement)
    public Optional<Movie> getPopularMovies() {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/movie/popular")
                            .queryParam("api_key", apiKey)
                            .build())
                    .retrieve()
                    .bodyToFlux(Movie.class)
                    .toStream()
                    .findFirst();
        } catch (WebClientResponseException ex) {
            System.err.println("Error fetching popular movies: " + ex.getMessage());
            return Optional.empty();
        }
    }
}
