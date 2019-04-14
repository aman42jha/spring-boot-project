package com.login.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.login.model.Counter;
public interface CounterRepository extends MongoRepository<Counter, String> {

}
