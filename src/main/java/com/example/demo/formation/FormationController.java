package com.example.demo.formation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")

    @RequestMapping("/formation")
    public class FormationController {
        private final FormationService service;

        public FormationController(FormationService service) {
            this.service = service;}

     @GetMapping
        public ResponseEntity<List<Formation>> findAll() {
            List<Formation> items = service.findAll();
            return ResponseEntity.ok().body(items);
        }
    @GetMapping("/{id}")
    public ResponseEntity<Formation> find(@PathVariable("id") Long id) {
        Optional<Formation> item = service.find(id);
        return ResponseEntity.of(item);
    }
    @PostMapping
    public ResponseEntity<Formation> create(@RequestBody Formation item) {
        Formation created = service.create(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Formation> update(
            @PathVariable("id") Long id,
            @RequestBody Formation updatedItem) {

        Optional<Formation> updated = service.update(id, updatedItem);

        return updated
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                    Formation created = service.create(updatedItem);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(created.getId())
                            .toUri();
                    return ResponseEntity.created(location).body(created);
                });
    }

    // ✨ New! 👇 DELETE definition ✨
    @DeleteMapping("/{id}")
    public ResponseEntity<Formation> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

