package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.repository.impl.CategoryRepository;
import mk.ukim.finki.wp.lab.repository.jpa.CategoryRepositoryJPA;
import mk.ukim.finki.wp.lab.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepositoryJPA categoryRepositoryJPA;

    public CategoryServiceImpl(CategoryRepositoryJPA categoryRepositoryJPA) {
        this.categoryRepositoryJPA = categoryRepositoryJPA;
    }

    @Override
    public List<Category> listAll() {
        return this.categoryRepositoryJPA.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return this.categoryRepositoryJPA.findById(id);
    }
}
