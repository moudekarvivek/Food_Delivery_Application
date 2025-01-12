package com.substring.foodie.service;

import com.substring.foodie.dto.RestaurantDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RestaurantService {

    RestaurantDto saveRestaurant(RestaurantDto restaurantDto);
    RestaurantDto updateRestaurant(RestaurantDto restaurantDto, String restaurantId);

    Page<RestaurantDto> getAll(Pageable pageable);

    List<RestaurantDto> getRestaurantByName(String restaurantName);

    RestaurantDto getRestaurantById(String restaurantId);

    void deleteRestaurant(String restaurantId);
}
