package com.iqmsoft.boot.vue;

import com.alibaba.fastjson.JSON;
import com.iqmsoft.boot.vue.domain.Cookbook;
import com.iqmsoft.boot.vue.domain.User;
import com.iqmsoft.boot.vue.domain.UserDishes;
import com.iqmsoft.boot.vue.repository.CookbookRepository;
import com.iqmsoft.boot.vue.repository.UserDishesRepository;
import com.iqmsoft.boot.vue.repository.UserRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootVueAppTests {

    @Resource
    private UserRepository userRepository;

    @Resource
    private CookbookRepository cookbookRepository;

    @Resource
    private UserDishesRepository userDishesRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void test() throws Exception {
     
      

       userRepository.save(new User(3L, "kaka"));
       Assert.assertEquals(1, userRepository.findAll().size());
   
  
        User u = userRepository.findByName("kaka");
        log.info("u:{}", u.toString());
    	
    }

  
    @Test
    public void test_initCookBookData() {
        cookbookRepository.deleteAll();
        Cookbook cookbook1 = new Cookbook("test1", 10, 20);
        Cookbook cookbook2 = new Cookbook("test2", 8, 18);
        Cookbook cookbook3 = new Cookbook("test3", 7, 10);
        cookbookRepository.save(cookbook1);
        cookbookRepository.save(cookbook2);
        cookbookRepository.save(cookbook3);
        log.info("result:{}", JSON.toJSONString(cookbookRepository.findAll()));
    }


    @Test
    public void test_initDishesData() {

        userDishesRepository.deleteAll();
        UserDishes userDishes1 = new UserDishes("crap", "test1", LocalDate.now(), 1000);
        UserDishes userDishes2 = new UserDishes("crap", "test2", LocalDate.now(), 1000);

        userDishesRepository.save(userDishes1);
        userDishesRepository.save(userDishes2);
        log.info("result:{}", JSON.toJSONString(userDishesRepository.findAll()));
    }

  
}
