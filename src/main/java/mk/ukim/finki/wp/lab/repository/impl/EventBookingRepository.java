package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventBookingRepository {

    public List<EventBooking> listAll() {
        return DataHolder.reservations;
    }

    public Optional<EventBooking> save(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets) {
        EventBooking eventBooking = new EventBooking(eventName, attendeeName, attendeeAddress, numberOfTickets);
        DataHolder.reservations.add(eventBooking);
        return Optional.of(eventBooking);
    }
}
