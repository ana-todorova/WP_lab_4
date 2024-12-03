package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.impl.LocationRepository;
import mk.ukim.finki.wp.lab.repository.jpa.LocationRepositoryJPA;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepositoryJPA locationRepositoryJPA;

    public LocationServiceImpl(LocationRepositoryJPA locationRepositoryJPA) {
        this.locationRepositoryJPA = locationRepositoryJPA;
    }

    @Override
    public List<Location> findAll() {
        return this.locationRepositoryJPA.findAll();
    }

    @Override
    public Optional<Location> findById(Long id){
        return this.locationRepositoryJPA.findById(id);
    }
}
