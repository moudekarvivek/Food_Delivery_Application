package com.substring.foodie.repository;

import com.substring.foodie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    //Custom Query Method if necessary
    List<User> findByName(String name);
    Optional<User> findByEmail(String email);
}
