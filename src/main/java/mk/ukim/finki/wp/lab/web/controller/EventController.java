package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.service.CategoryService;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final CategoryService categoryService;
    private final LocationService locationService;
    private final EventBookingService eventBookingService;

    public EventController(EventService eventService, CategoryService categoryService, LocationService locationService, EventBookingService eventBookingService) {
        this.eventService = eventService;
        this.categoryService = categoryService;
        this.locationService = locationService;
        this.eventBookingService = eventBookingService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String searchEvents,
                                @RequestParam(required = false) Double minPopularityScore,
                                @RequestParam(required = false) String searchCategory,
                                Model model) {

        List<Event> events = eventService.findAll();

        if (!events.isEmpty()) {
            if (searchEvents != null && minPopularityScore != null && searchCategory != null) {
                model.addAttribute("events", this.eventService.searchEvents(searchEvents, minPopularityScore, searchCategory));
            } else {
                model.addAttribute("events", this.eventService.findAll());
            }
        } else {
            model.addAttribute("hasError", true);
            model.addAttribute("error", "There aren't any events to display at the moment.");
        }
        return "listEvents";
    }

    @GetMapping("/by-location")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEventsByLocation(@RequestParam Long searchLocation, Model model) {
        List<Event> events = eventService.findAllByLocation_Id(searchLocation);

        if (events.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", "No events found for the specified location.");
        } else {
            model.addAttribute("events", events);
        }
        return "listEvents";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteEvent(@PathVariable Long id) {
        this.eventService.deleteEventById(id);
        return "redirect:/events";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEDitEventForm(@PathVariable Long id, Model model) {
        if (this.eventService.findEventById(id).isPresent()) {
            Event event = this.eventService.findEventById(id).get();
            List<Category> categories = this.categoryService.listAll();
            List<Location> locations = this.locationService.findAll();
            model.addAttribute("event", event);
            model.addAttribute("categories", categories);
            model.addAttribute("locations", locations);
            return "add-event";
        }
        return "redirect:/events?error=EventNotFound";
    }


    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddEventPage(Model model) {
        List<Category> categories = this.categoryService.listAll();
        List<Location> locations = this.locationService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("locations", locations);
        return "add-event";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String save(@RequestParam(required = false) Long id,
                       @RequestParam String name,
                       @RequestParam String description,
                       @RequestParam Double popularityScore,
                       @RequestParam Long category,
                       @RequestParam Long location) {

        if (id != null) {
            this.eventService.saveEvent(id, name, description, popularityScore, category, location);
        } else {
            this.eventService.saveEvent(null, name, description, popularityScore, category, location);
        }
        return "redirect:/events";
    }

    @PostMapping("/bookEvent")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String bookEvent(@RequestParam String attendeeName,
                            @RequestParam String eventName,
                            @RequestParam Long numberOfTickets, HttpServletRequest req) {

        String attendeeAddress = req.getRemoteAddr();
        eventBookingService.save(eventName, attendeeName, attendeeAddress, numberOfTickets);
        return "redirect:/eventBooking";
    }
}