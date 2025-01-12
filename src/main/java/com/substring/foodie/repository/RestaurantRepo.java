package com.substring.foodie.repository;

import com.substring.foodie.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, String> {
    List<Restaurant> findByNameContainingIgnoreCase(String restaurantName);
}
