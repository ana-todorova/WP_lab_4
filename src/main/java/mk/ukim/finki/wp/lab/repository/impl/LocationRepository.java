package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Location;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class LocationRepository {
    public List<Location> findAll() {
        return DataHolder.locations;
    }

    public Optional<Location> findById(Long id) {
        return DataHolder.locations.stream()
                .filter(l -> l.getId().equals(id)).findFirst();
    }
}
