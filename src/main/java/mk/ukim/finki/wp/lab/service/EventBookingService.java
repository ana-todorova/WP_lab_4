package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.EventBooking;

import java.util.List;
import java.util.Optional;

public interface EventBookingService {
    EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets);

    List<EventBooking> listAll();

    Optional<EventBooking> save(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets);

}
