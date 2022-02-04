package com.example.mongobatch.repository;

import com.example.mongobatch.object.TestObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestObjectRepository extends MongoRepository<TestObject, String> {
}
