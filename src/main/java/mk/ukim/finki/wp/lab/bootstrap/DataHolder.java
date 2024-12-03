package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = null;
    public static List<Category> categories = null;
    public static List<Location> locations = null;
    public static List<EventBooking> reservations = null;

    @PostConstruct
    public void init() {

        events = new ArrayList<>();
        categories = new ArrayList<>();
        locations = new ArrayList<>();
        reservations = new ArrayList<>();

        Category category1 = new Category("Category1");
        Category category2 = new Category("Category2");
        Category category3 = new Category("Category3");

        Location location1 = new Location("Location1", "Address1", "100", "Description1");
        Location location2 = new Location("Location2", "Address2", "200", "Description2");
        Location location3 = new Location("Location3", "Address3", "300", "Description3");
        Location location4 = new Location("Location4", "Address4", "400", "Description4");
        Location location5 = new Location("Location5", "Address5", "500", "Description5");

        categories.add(category1);
        categories.add(category2);
        categories.add(category3);

        locations.add(location1);
        locations.add(location2);
        locations.add(location3);
        locations.add(location4);
        locations.add(location5);

        events.add(new Event("Concert", "Music concert in the park", 1.00, category1, location3));
        events.add(new Event("Workshop", "Web development workshop", 2.00, category2, location1));
        events.add(new Event("Conference", "Tech conference 2024", 3.00, category3, location5));
        events.add(new Event("Art Exhibition", "Modern art gallery opening", 3.00, category1, location2));
        events.add(new Event("Marathon", "City marathon event", 4.00, category2, location4));
        events.add(new Event("Festival", "Annual food and drink festival", 5.00, category3, location1));
        events.add(new Event("Seminar", "Business growth seminar", 6.00, category1, location2));
        events.add(new Event("Book Launch", "Launch of a new book by a local author", 7.00, category2, location3));
        events.add(new Event("Theater Play", "Drama play at the local theater", 8.00, category3, location4));
        events.add(new Event("Movie Night", "Outdoor movie night screening", 9.00, category1, location5));

    }
}
