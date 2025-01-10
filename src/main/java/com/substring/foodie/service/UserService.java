package com.substring.foodie.service;

import com.substring.foodie.dto.UserDto;
import com.substring.foodie.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, String userId);

    Page<UserDto> getAll(Pageable pageable);

    List<UserDto> getUserByName(String userName);
    UserDto getUserByEmail(String email);
    UserDto getUserById(String userId);
    void deleteUser(String userId);

}
