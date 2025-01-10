package com.substring.foodie.services;

import com.substring.foodie.entity.Restaurant;
import com.substring.foodie.entity.Role;
import com.substring.foodie.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class UserService {

//    @Autowired
//    private com.substring.foodie.service.UserService userService;
//
//    @Test
//    public void testSaveUser(){
//        System.out.println("Saving User");
//
//        //user
//
//         User user = new User();
//         user.setName("Vivek");
//         user.setEmail("vivek@gmail.com");
//         user.setAvailable(true);
//         user.setAddress("Test address, DELHI");
//         user.setRole(Role.ADMIN);
//
//        //Restaurant
//
//        Restaurant restaurant = new Restaurant();
//        restaurant.setId(UUID.randomUUID().toString());
//        restaurant.setName("Dominos");
//        restaurant.setAddress("Pune");
//        restaurant.setOpen(true);
//
//        Restaurant restaurant1 = new Restaurant();
//        restaurant1.setId(UUID.randomUUID().toString());
//        restaurant1.setName("Haldiram");
//        restaurant1.setAddress("Pune");
//        restaurant1.setOpen(true);
//
//        restaurant1.setUser(user);
//        restaurant.setUser(user);
//
//        user.getRestaurants().add(restaurant);
//        user.getRestaurants().add(restaurant1);
//
//        User saved = userService.saveUser(user);
//
//        System.out.println(saved.getName());
//    }
}
