package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {

    public List<Event> findAll() {
        return DataHolder.events;
    }

    public List<Event> searchEvents(String text, Double minPopularityScore, String categoryName) {
        return DataHolder.events.stream().filter(
                e -> e.getName().toLowerCase().contains(text.toLowerCase()) && e.getPopularityScore() >= minPopularityScore && e.getCategory().getCategoryName().toLowerCase().contains(categoryName.toLowerCase())).toList();
    }

    public Optional<Event> save(String name, String description, Double popularityScore, Category category, Location location) {
        DataHolder.events.removeIf(e -> e.getName().equals(name));
        Event event = new Event(name, description, popularityScore, category, location);
        DataHolder.events.add(event);
        return Optional.of(event);
    }

    public Optional<Event> findById(Long id) {
        return DataHolder.events.
                stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id) {
        DataHolder.events.removeIf(e -> e.getId().equals(id));
    }

}
