package com.substring.foodie.service;

import com.substring.foodie.dto.RestaurantDto;
import com.substring.foodie.entity.Restaurant;
import com.substring.foodie.exception.ResourceNotFoundException;
import com.substring.foodie.repository.RestaurantRepo;
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

    //Constructor Injection
    public RestaurantServiceImpl(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    @Override
    public RestaurantDto saveRestaurant(RestaurantDto restaurantDto) {
        // Generate New id for Restaurant
        restaurantDto.setId(UUID.randomUUID().toString());

        Restaurant restaurant = convertRestaurantDtoToRestaurant(restaurantDto);

        //Saving Restaurant to the database
        Restaurant savedRestaurant = restaurantRepo.save(restaurant);

        return convertRestaurantToRestaurantDto(savedRestaurant);
    }

    @Override
    public RestaurantDto updateRestaurant(RestaurantDto restaurantDto, String restaurantId) {
        return null;
    }

    @Override
    public Page<RestaurantDto> getAll(Pageable pageable) {
        Page<Restaurant> restaurantPage = restaurantRepo.findAll(pageable);

        return restaurantPage.map(restaurant -> convertRestaurantToRestaurantDto(restaurant));
    }

    @Override
    public List<RestaurantDto> getRestaurantByName(String restaurantName) {
        return restaurantRepo.findByName(restaurantName)
                .stream()
                .map((restaurant) -> convertRestaurantToRestaurantDto(restaurant))
                .toList();
    }

    @Override
    public RestaurantDto getRestaurantById(String restaurantId) {
       Restaurant restaurant = restaurantRepo.findById(restaurantId)
               .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

       return convertRestaurantToRestaurantDto(restaurant);
    }

    @Override
    public void deleteRestaurant(String restaurantId) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        restaurantRepo.delete(restaurant);
    }

    public Restaurant convertRestaurantDtoToRestaurant(RestaurantDto restaurantDto){
        Restaurant restaurant = new Restaurant();
        BeanUtils.copyProperties(restaurant, restaurantDto);
        return restaurant;
    }
    public RestaurantDto convertRestaurantToRestaurantDto(Restaurant restaurant){
        RestaurantDto restaurantDto = new RestaurantDto();
        BeanUtils.copyProperties(restaurantDto, restaurant);
        return restaurantDto;
    }
}
