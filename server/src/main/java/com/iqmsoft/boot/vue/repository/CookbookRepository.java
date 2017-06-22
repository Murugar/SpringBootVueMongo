package com.iqmsoft.boot.vue.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.iqmsoft.boot.vue.domain.Cookbook;


public interface CookbookRepository extends MongoRepository<Cookbook, Long> {

    Cookbook findByName(String name);

}
