package hh.sof3.moviemania.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieid;
    private String title;
    private String director;
    private Integer releaseyear;
    private String description;
    private String review;

    @ManyToMany
    @JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movieid"), inverseJoinColumns = @JoinColumn(name= "genreid"))
    @JsonIgnoreProperties("movies")
    private Set<Genre> genre;

    public Movie(String title, String director, Integer releaseyear, String description, String review, Set<Genre> genre) {
        this.title = title;
        this.director = director;
        this.releaseyear = releaseyear;
        this.description = description;
        this.review = review;
        this.genre = genre;
    }

    public Movie(){

    }

    public Long getMovieid() {
        return movieid;
    }

    public void setMovieid(Long movieid) {
        this.movieid = movieid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(Integer releaseyear) {
        this.releaseyear = releaseyear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Set<Genre> getGenre() {
        return genre;
    }

    public void setGenre(Set<Genre> genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "movieid = " + movieid + ", title = " + title + "director = " + director + ", releaseyear = " + releaseyear + ", description = "
                + description + ", review = " + review + ", genre = " + genre;
    }
    
}
