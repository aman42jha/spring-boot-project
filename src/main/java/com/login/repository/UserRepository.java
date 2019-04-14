package com.login.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.login.model.Login;

public interface UserRepository extends MongoRepository<Login, Integer>, QuerydslPredicateExecutor<Login> {

}
 