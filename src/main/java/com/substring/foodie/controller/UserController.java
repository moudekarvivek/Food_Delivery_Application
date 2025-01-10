package com.substring.foodie.controller;

import com.substring.foodie.dto.UserDto;
import com.substring.foodie.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //Create User
    @PostMapping
    public ResponseEntity<UserDto> create(
            @RequestBody UserDto userDto
    ){
        UserDto userDtoResult = userService.saveUser(userDto);
        return ResponseEntity.ok(userDtoResult);
    }

    //Get All User
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    //Get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable("userId") String id){

        return ResponseEntity.ok(userService.getUserById(id));
    }
}
