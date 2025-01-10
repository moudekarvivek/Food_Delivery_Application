package com.substring.foodie.controller;

import com.substring.foodie.dto.UserDto;
import com.substring.foodie.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Page<UserDto>> findAll(
            @RequestParam(value = "page",required = false,defaultValue = "0") int page,
            @RequestParam(value = "size",required = false,defaultValue = "10") int size,
            @RequestParam(value = "sortBy",required = false,defaultValue = "createdDate") String sortBy,
            @RequestParam(value = "sortDir",required = false,defaultValue = "desc") String sortDir
    ){
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.ok(userService.getAll(pageable));
    }

    //Get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable("userId") String id){

        return ResponseEntity.ok(userService.getUserById(id));
    }
}
