package com.ckno.petproject.adapters.igp;

import com.ckno.petproject.adapters.igp.dto.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGpRepository extends MongoRepository<Driver, String> {
}
