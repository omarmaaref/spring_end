package com.example.demo.review;

import com.example.demo.formation.Formation;
import com.example.demo.formation.FormationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
    @RestController
    @CrossOrigin("http://localhost:4200")
    @RequestMapping("/review")
    public class ReviewController {
        private final ReviewService service;

        public ReviewController(ReviewService service) {
            this.service = service;}

        @GetMapping
        public ResponseEntity<List<Review>> findAll() {
            List<Review> items = service.findAll();
            return ResponseEntity.ok().body(items);
        }
        @GetMapping("/{id}")
        public ResponseEntity<Review> find(@PathVariable("id") Long id) {
            Optional<Review> item = service.find(id);
            return ResponseEntity.of(item);
        }
        @PostMapping
        public ResponseEntity<Review> create(@RequestBody Review item) {
            Review created = service.create(item);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(created.getId())
                    .toUri();
            return ResponseEntity.created(location).body(created);
        }
        @PutMapping("/{id}")
        public ResponseEntity<Review> update(
                @PathVariable("id") Long id,
                @RequestBody Review updatedItem) {

            Optional<Review> updated = service.update(id, updatedItem);

            return updated
                    .map(value -> ResponseEntity.ok().body(value))
                    .orElseGet(() -> {
                        Review created = service.create(updatedItem);
                        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(created.getId())
                                .toUri();
                        return ResponseEntity.created(location).body(created);
                    });
        }

        // ✨ New! 👇 DELETE definition ✨
        @DeleteMapping("/{id}")
        public ResponseEntity<Review> delete(@PathVariable("id") Long id) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
    }



