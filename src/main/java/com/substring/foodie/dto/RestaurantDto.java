package com.substring.foodie.dto;

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
    private String address;
    private LocalTime openTime;
    private LocalTime closeTIme;
    private Boolean open = true;
}
