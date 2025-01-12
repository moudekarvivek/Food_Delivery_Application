package com.substring.foodie.controller;

import com.substring.foodie.dto.RestaurantDto;
import com.substring.foodie.service.RestaurantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    //Constructor Injection

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    //Creating Restaurant
    @PostMapping
    public ResponseEntity<RestaurantDto> create(
            @RequestBody RestaurantDto restaurantDto
    ){
        RestaurantDto restaurantDtoResult = restaurantService.saveRestaurant(restaurantDto);
        return ResponseEntity.ok(restaurantDtoResult);
    }

    //Update Restaurant
    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> update(
            @RequestBody RestaurantDto restaurantDto,
            @PathVariable String restaurantId){

        RestaurantDto restaurantDto1 = restaurantService.updateRestaurant(restaurantDto, restaurantId);
        return ResponseEntity.ok(restaurantDto1);
    }

    //Get All Restaurant
    @GetMapping
    public ResponseEntity<Page<RestaurantDto>> findAll(
            @RequestParam(value = "page",required = false,defaultValue = "0") int page,
            @RequestParam(value = "size",required = false,defaultValue = "10") int size,
            @RequestParam(value = "sortBy",required = false,defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir",required = false,defaultValue = "desc") String sortDir
    ){
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(restaurantService.getAll(pageable));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> findById(@PathVariable("restaurantId") String id){
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }
}
