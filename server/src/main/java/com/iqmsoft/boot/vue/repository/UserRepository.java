package com.iqmsoft.boot.vue.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.iqmsoft.boot.vue.domain.User;


public interface UserRepository extends MongoRepository<User, Long> {
    User findByName(String name);
}