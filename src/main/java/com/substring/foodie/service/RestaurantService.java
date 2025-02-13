package com.substring.foodie.service;

import com.substring.foodie.dto.RestaurantDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RestaurantService {

    RestaurantDto saveRestaurant(RestaurantDto restaurantDto);

    RestaurantDto updateRestaurant(RestaurantDto restaurantDto, String restaurantId);

    //Get ALL
    Page<RestaurantDto> getAll(Pageable pageable);

    List<RestaurantDto> getRestaurantByName(String restaurantName);

    RestaurantDto getRestaurantById(String restaurantId);

    void deleteRestaurant(String restaurantId);

    RestaurantDto uploadBanner(MultipartFile file, String id) throws IOException;
}
