package com.example.demo.formation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InMemoryFormationRepository extends CrudRepository<Formation, Long> {}
