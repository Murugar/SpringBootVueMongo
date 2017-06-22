package com.iqmsoft.boot.vue.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.iqmsoft.boot.vue.domain.UserDishes;


public interface UserDishesRepository extends MongoRepository<UserDishes, Long> {

}
