package com.substring.foodie.repository;

import com.substring.foodie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
    //Custom Query Method if necessary
}
