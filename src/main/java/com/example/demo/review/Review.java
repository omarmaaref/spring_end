package com.example.demo.review;

import com.example.demo.formation.Formation;
import org.springframework.data.annotation.Id;

public class Review {
    private final Long id;
    private final Long rating;

    private final Formation formation;
    public Review(Long id, Long rating, Formation formation) {
        this.id = id;
        this.rating= rating;
        this.formation=formation;
    }

    public Formation getFormation() {
        return formation;
    }

    public Long getRating() {
        return rating;
    }

    @Id
    public Long getId() {
        return id;
    }


    public Review updateWith(Review review){
        return new Review(this.id,review.getRating()
                ,review.getFormation());
    }
}

