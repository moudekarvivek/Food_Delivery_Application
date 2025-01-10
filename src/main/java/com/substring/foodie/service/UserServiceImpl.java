package com.substring.foodie.service;

import com.substring.foodie.dto.UserDto;
import com.substring.foodie.entity.User;
import com.substring.foodie.exception.ResourceNotFoundException;
import com.substring.foodie.repository.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private UserRepo userRepo;

    //Constructor Injection
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        //Generate new id for user
        userDto.setId(UUID.randomUUID().toString());

        User user= convertUserDtoToUser(userDto);

        //Save the User to the database
        User savedUser = userRepo.save(user);

        return convertUserToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        return null;
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable){

       Page<User> userPage = userRepo.findAll(pageable);
        //Converting each user entity to userDto using stream

        // return after converting to Page<UserDto>
       return userPage.map(user -> convertUserToUserDto(user));
    }

    @Override
    public List<UserDto> getUserByName(String userName) {
        return userRepo.findByName(userName)
                .stream()
                .map((user) ->convertUserToUserDto(user))
                .toList();
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found!!"));

        return convertUserToUserDto(user);
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found!!"));

        return convertUserToUserDto(user);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found!!"));

        userRepo.delete(user);
    }

    //Creating object to convert UserDto object to User Entity to interact with database
    private User convertUserDtoToUser(UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

    private UserDto convertUserToUserDto(User user){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

//    private UserRepo userRepo;
//
//    public UserServiceImpl(UserRepo userRepo) {
//        this.userRepo = userRepo;
//    }
//
//    @Override
//    public User saveUser(User user) {
//        user.setId(UUID.randomUUID().toString());
//        User savedEntity= userRepo.save(user);
//        return savedEntity;
//    }
}
