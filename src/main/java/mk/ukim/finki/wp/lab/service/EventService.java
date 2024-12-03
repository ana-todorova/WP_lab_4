package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> findAll();

    List<Event> searchEvents(String searchEvents, Double minPopularityScore, String searchCategory);

    Optional<Event> saveEvent(Long id, String eventName, String description, double popularityScore, Long categoryId, Long locationId);

    Optional<Event> findEventById(Long id);

    void deleteEventById(Long eventId);
    List<Event> findAllByLocation_Id(Long locationId);
}