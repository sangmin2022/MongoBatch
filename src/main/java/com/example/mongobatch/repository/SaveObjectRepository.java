package com.example.mongobatch.repository;

import com.example.mongobatch.object.SaveObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SaveObjectRepository extends MongoRepository<SaveObject, String> {
}
