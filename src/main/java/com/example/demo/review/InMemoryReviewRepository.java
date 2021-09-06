package com.example.demo.review;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository

    public interface InMemoryReviewRepository extends CrudRepository<Review, Long> {}


