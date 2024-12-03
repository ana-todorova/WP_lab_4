package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public class CategoryRepository {

    public List<Category> findAll(){
        return DataHolder.categories;
    }

    public Optional<Category> findById(Long id) {
        return DataHolder.categories.stream()
                .filter(c -> c.getId().equals(id)).findFirst();
    }
}
