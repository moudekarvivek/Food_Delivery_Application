package com.substring.foodie.service;

import com.substring.foodie.entity.User;
import com.substring.foodie.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User saveUser(User user) {
        user.setId(UUID.randomUUID().toString());
        User savedEntity= userRepo.save(user);
        return savedEntity;
    }
}
