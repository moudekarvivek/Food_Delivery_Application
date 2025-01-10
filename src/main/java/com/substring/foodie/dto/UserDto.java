package com.substring.foodie.dto;

// for providing data to the service layer working with view

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder //Used for creating Object provide builder pattern
public class UserDto {
    private String id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
}
