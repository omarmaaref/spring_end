package com.example.demo.formation;
import org.springframework.data.annotation.Id;

public class Formation {
    private final Long id;
    private final String name;
    private final String description;

    public Formation(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    @Id
    public Long getId() {
        return id;
    }


    public Formation updateWith(Formation form){
        return new Formation(this.id,form.getName()
                ,form.getDescription());
    }
}
