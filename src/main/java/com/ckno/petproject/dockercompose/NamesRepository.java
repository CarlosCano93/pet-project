package com.ckno.petproject.dockercompose;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NamesRepository extends MongoRepository<Person, String> {

}

record Person(String name) {}
