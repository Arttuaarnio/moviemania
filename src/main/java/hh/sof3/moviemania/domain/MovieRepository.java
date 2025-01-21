package hh.sof3.moviemania.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    // Checks for an existing movie by title and release year
    boolean existsByTitleAndReleaseyear(String title, Integer releaseyear);

    // Finds a movie by the title and release year
    Optional<Movie> findByTitleAndReleaseyear(String title, Integer releaseyear);

}
