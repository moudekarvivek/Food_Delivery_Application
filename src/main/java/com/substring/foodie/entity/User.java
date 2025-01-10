package com.substring.foodie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "foodie_users")
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role; // ADMIN, CUSTOMER, DELIVERY_BOY

    private boolean isAvailable=true; // applicable for delivery boy

    //feel free to add more fields as required
    //orphanRemoval used to affect the operation in database also
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Restaurant> Restaurants = new ArrayList<>();

}
