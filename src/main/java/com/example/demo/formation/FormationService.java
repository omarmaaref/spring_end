package com.example.demo.formation;

        import org.springframework.data.map.repository.config.EnableMapRepositories;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Service;

        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;
        import java.util.Optional;

@Service
@EnableMapRepositories
public class FormationService {
    private final CrudRepository<Formation, Long> repository;
    public FormationService(CrudRepository<Formation, Long> repository) {
        this.repository = repository;
        this.repository.saveAll(defaultItems());
    }

    private static List<Formation> defaultItems() {
        return List.of(
                new Formation(1L, "java", "formation mt3 javaa"),
                new Formation(2L, "c++", "formation mt3 c++")
        );
    }
    public List<Formation> findAll() {
        List<Formation> list = new ArrayList<>();
        Iterable<Formation> items = repository.findAll();
        items.forEach(list::add);
        return list;
    }

    public Optional<Formation> find(Long id) {
        return repository.findById(id);
    }

    public Formation create(Formation item) {
        // To ensure the item ID remains unique,
        // use the current timestamp.
        Formation copy = new Formation(
                new Date().getTime(),
                item.getName(),
                item.getDescription()
        );
        return repository.save(copy);
    }

    public Optional<Formation> update( Long id, Formation newItem) {
        // Only update an item if it can be found first.
        return repository.findById(id)
                .map(oldItem -> {
                    Formation updated = oldItem.updateWith(newItem);
                    return repository.save(updated);
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

