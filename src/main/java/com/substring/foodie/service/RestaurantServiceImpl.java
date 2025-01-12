package com.substring.foodie.service;

import com.substring.foodie.dto.RestaurantDto;
import com.substring.foodie.entity.Restaurant;
import com.substring.foodie.exception.ResourceNotFoundException;
import com.substring.foodie.repository.RestaurantRepo;
import com.substring.foodie.utils.Helper;
import jakarta.persistence.Id;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.UUID;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepo restaurantRepo;
    private ModelMapper modelMapper;

    //Constructor Injection
    public RestaurantServiceImpl(RestaurantRepo restaurantRepo, ModelMapper modelMapper) {
        this.restaurantRepo = restaurantRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public RestaurantDto saveRestaurant(RestaurantDto restaurantDto) {
        // Generate New id for Restaurant
        restaurantDto.setId(Helper.generateRandomId());

        Restaurant restaurant = modelMapper.map(restaurantDto, Restaurant.class);
        //Saving Restaurant to the database
        Restaurant savedRestaurant = restaurantRepo.save(restaurant);

        return modelMapper.map(savedRestaurant, RestaurantDto.class);
    }

    @Override
    public RestaurantDto updateRestaurant(RestaurantDto restaurantDto, String restaurantId) {

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant Not Found"));
       restaurant.setName(restaurantDto.getName());
       restaurant.setAddress(restaurantDto.getAddress());
       restaurant.setDescription(restaurantDto.getDescription());
       restaurant.setOpenTime(restaurantDto.getOpenTime());
       restaurant.setCloseTIme(restaurantDto.getCloseTIme());
       restaurant.setOpen(restaurantDto.getOpen());

       Restaurant savedEntity = restaurantRepo.save(restaurant);
        return modelMapper.map(savedEntity, RestaurantDto.class);
    }

    @Override
    public Page<RestaurantDto> getAll(Pageable pageable) {
        Page<Restaurant> restaurantPage = restaurantRepo.findAll(pageable);

        return restaurantPage.map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class));
    }

    @Override
    public List<RestaurantDto> getRestaurantByName(String restaurantName) {
        return restaurantRepo.findByNameContainingIgnoreCase(restaurantName)
                .stream()
                .map((restaurant) -> modelMapper.map(restaurant, RestaurantDto.class))
                .toList();
    }

    @Override
    public RestaurantDto getRestaurantById(String restaurantId) {
       Restaurant restaurant = restaurantRepo.findById(restaurantId)
               .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
       return modelMapper.map(restaurant, RestaurantDto.class);
    }

    @Override
    public void deleteRestaurant(String restaurantId) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        restaurantRepo.delete(restaurant);
    }

//    public Restaurant convertRestaurantDtoToRestaurant(RestaurantDto restaurantDto){
//        Restaurant restaurant = new Restaurant();
//        BeanUtils.copyProperties(restaurant, restaurantDto);
//        return restaurant;
//    }
//    public RestaurantDto convertRestaurantToRestaurantDto(Restaurant restaurant){
//        RestaurantDto restaurantDto = new RestaurantDto();
//        BeanUtils.copyProperties(restaurantDto, restaurant);
//        return restaurantDto;
//    }
}
