package com.ckno.petproject.adapters.igp;

import com.ckno.petproject.adapters.igp.dto.ProDriver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGpRepository extends MongoRepository<ProDriver, String> {
}
