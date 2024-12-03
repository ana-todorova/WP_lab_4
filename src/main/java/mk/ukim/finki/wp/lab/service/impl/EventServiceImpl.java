package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.EventNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.LocationNotFoundException;
import mk.ukim.finki.wp.lab.repository.impl.CategoryRepository;
import mk.ukim.finki.wp.lab.repository.impl.EventRepository;
import mk.ukim.finki.wp.lab.repository.impl.LocationRepository;
import mk.ukim.finki.wp.lab.repository.jpa.CategoryRepositoryJPA;
import mk.ukim.finki.wp.lab.repository.jpa.EventRepositoryJPA;
import mk.ukim.finki.wp.lab.repository.jpa.LocationRepositoryJPA;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepositoryJPA eventRepositoryJPA;
    private final CategoryRepositoryJPA categoryRepositoryJPA;
    private final LocationRepositoryJPA locationRepositoryJPA;


    public EventServiceImpl(EventRepositoryJPA eventRepositoryJPA, CategoryRepositoryJPA categoryRepositoryJPA, LocationRepositoryJPA locationRepositoryJPA) {
        this.eventRepositoryJPA = eventRepositoryJPA;
        this.categoryRepositoryJPA = categoryRepositoryJPA;
        this.locationRepositoryJPA = locationRepositoryJPA;
    }

    @Override
    public List<Event> findAll() {
        return this.eventRepositoryJPA.findAll();
    }

    @Override
    public List<Event> searchEvents(String searchEvents, Double minPopularityScore, String searchCategory) {
        return this.eventRepositoryJPA.searchEvents(searchEvents, minPopularityScore, searchCategory);
    }

    @Override
    public Optional<Event> saveEvent(Long id, String eventName, String description, double popularityScore, Long categoryId, Long locationId) {

        Category category = this.categoryRepositoryJPA.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Location location = this.locationRepositoryJPA.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));

        if (id != null) {
            Optional<Event> existingEvent = this.eventRepositoryJPA.findById(id);
            if(existingEvent.isPresent()){
                Event event = existingEvent.get();
                event.setName(eventName);
                event.setDescription(description);
                event.setPopularityScore(popularityScore);
                event.setCategory(category);
                event.setLocation(location);
                return Optional.of(this.eventRepositoryJPA.save(event));
            } else{
                throw new EventNotFoundException(id);
            }
        } else {
            Event newEvent = new Event(eventName, description, popularityScore, category, location);
            return Optional.of(this.eventRepositoryJPA.save(newEvent));
        }
    }

    @Override
    public Optional<Event> findEventById(Long id) {
        return this.eventRepositoryJPA.findById(id);
    }

    @Override
    public void deleteEventById(Long eventId) {
        this.eventRepositoryJPA.deleteById(eventId);
    }

    @Override
    public List<Event> findAllByLocation_Id(Long locationId) {
        return this.eventRepositoryJPA.findAllByLocation_Id(locationId);
    }

}
