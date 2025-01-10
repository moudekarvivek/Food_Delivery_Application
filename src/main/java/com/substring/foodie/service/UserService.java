package com.substring.foodie.service;

import com.substring.foodie.dto.UserDto;
import com.substring.foodie.entity.User;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, String userId);
    List<UserDto> getAll();
    List<UserDto> getUserByName(String userName);
    UserDto getUserByEmail(String email);
    UserDto getUserById(String userId);
    void deleteUser(String userId);

}
