package hh.sof3.moviemania.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // You can now use findAll() that returns List<Movie>
}
