package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double popularityScore;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Location location;
    @ManyToOne
    private User user;

    public Event(String name, String description, Double popularityScore, Category category, Location location) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.category = category;
        this.location = location;
    }

    public Event() {

    }
}
