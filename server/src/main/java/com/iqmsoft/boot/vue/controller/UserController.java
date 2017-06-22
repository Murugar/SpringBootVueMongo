package com.iqmsoft.boot.vue.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iqmsoft.boot.vue.domain.User;
import com.iqmsoft.boot.vue.domain.UserDishes;
import com.iqmsoft.boot.vue.repository.CookbookRepository;
import com.iqmsoft.boot.vue.repository.UserDishesRepository;

import java.time.LocalDate;

import javax.annotation.Resource;


@CrossOrigin
@RestController
public class UserController {

    @Resource
    private CookbookRepository cookbookRepository;

    @Resource
    private UserDishesRepository userDishesRepository;

    @GetMapping("/users")
    public User getUser() {
        return new User(1L, "test");
    }

    @GetMapping("cookbooks")
    public HttpEntity<?> cookbook() {
        return ResponseEntity.ok(cookbookRepository.findAll());
    }

    @GetMapping("userDishes")
    public HttpEntity<?> userDishes() {
        return ResponseEntity.ok(userDishesRepository.findAll());
    }

    @PostMapping("addDishes")
    public HttpEntity<?> addUserDishes(@RequestBody UserDishes userDishes) {
        String dishesName = userDishes.getDishesName();
        String userName = userDishes.getUserName();
       
        Integer price = cookbookRepository.findByName(userDishes.getDishesName()).getPrice();
        UserDishes saveOne = new UserDishes(userName, dishesName, LocalDate.now(), price);
        userDishesRepository.save(saveOne);
        return ResponseEntity.ok(saveOne);
    }

    @DeleteMapping("/userDishes/{id}")
    public HttpEntity<?> delete(@PathVariable("id") String id) {
        System.out.println(id);
        UserDishes deleOne = new UserDishes();
        deleOne.setId(id);
        userDishesRepository.delete(deleOne);
        return ResponseEntity.ok().build();
      }
}
