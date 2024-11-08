package hh.sof3.moviemania.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long suggestionid;

    // before naming the string "name" it was "suggestion" but it was causing a
    // mismatch for some reason so I changed it
    private String name;
    private String status;

    public Suggestion(String name, String status) {
        this.name = name;
        this.status = "pending";
    }

    public Suggestion() {
        this.status = "pending";
    }

    public Long getSuggestionid() {
        return suggestionid;
    }

    public void setSuggestionid(Long suggestionid) {
        this.suggestionid = suggestionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "suggestionid = " + suggestionid + ", suggestion = " + name + ", status = " + status ;
    }

}
