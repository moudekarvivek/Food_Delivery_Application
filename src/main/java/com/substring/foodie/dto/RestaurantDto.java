package com.substring.foodie.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDto {

    private String id;
    private String name;
    private String description;
    private String address;
    private LocalTime openTime;
    private LocalTime closeTIme;
    private Boolean open = true;
    //TODO:
    @JsonIgnore
    private String banner;

    //Getter Method
    @JsonProperty
    public String imageUrl(){
        return "http://localhost:8080/images" + banner;
    }
}
