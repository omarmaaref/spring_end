package com.example.demo.review;

import com.example.demo.formation.Formation;
import com.example.demo.formation.FormationService;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.stereotype.Service;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@EnableMapRepositories
public class ReviewService {
    private final CrudRepository<Review, Long> repository;

    public ReviewService(CrudRepository<Review, Long> repository) {
        this.repository = repository;
        this.repository.saveAll(defaultItems());
    }

    private static List<Review> defaultItems() {
        return List.of(
                new Review(1L, 55L, new Formation(1L, "java", "formation mt3 javaa")),
                new Review(2L, 55L,new Formation(2L, "c++", "formation mt3 c++") )
        );
    }
    public List<Review> findAll() {
        List<Review> list = new ArrayList<>();
        Iterable<Review> items = repository.findAll();
        items.forEach(list::add);
        return list;
    }

    public Optional<Review> find(Long id) {
        return repository.findById(id);
    }

    public Review create(Review item) {
        // To ensure the item ID remains unique,
        // use the current timestamp.
        Review copy = new Review(
                new Date().getTime(),
                item.getRating(),
                item.getFormation()
        );
        return repository.save(copy);
    }

    public Optional<Review> update( Long id, Review newItem) {
        // Only update an item if it can be found first.
        return repository.findById(id)
                .map(oldItem -> {
                    Review updated = oldItem.updateWith(newItem);
                    return repository.save(updated);
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

