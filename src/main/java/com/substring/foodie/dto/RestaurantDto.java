package com.substring.foodie.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss a")
    private LocalTime openTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss a")
    private LocalTime closeTIme;
    private Boolean open = true;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss a")
    private LocalDateTime createdDate;
    //TODO:

    private String banner;

    //Getter Method
    @JsonProperty
    public String imageUrl(){
        return "http://localhost:8080/images" + banner;
    }
}
