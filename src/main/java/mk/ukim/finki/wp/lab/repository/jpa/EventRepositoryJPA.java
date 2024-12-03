package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepositoryJPA extends JpaRepository<Event, Long> {
    List<Event> findAllByLocation_Id(Long locationId);
    List<Event> findAll();
    @Query("SELECT e FROM Event e WHERE " +
            "LOWER(e.name) LIKE LOWER(CONCAT('%', :text, '%')) AND " +
            "e.popularityScore >= :minRating AND " +
            "LOWER(e.category.categoryName) LIKE LOWER(CONCAT('%', :category, '%'))")
    List<Event> searchEvents(String text, Double minRating, String category);
    Optional<Event> findById(Long id);
    Event save(Event event);
    void deleteById(Long id);
}
