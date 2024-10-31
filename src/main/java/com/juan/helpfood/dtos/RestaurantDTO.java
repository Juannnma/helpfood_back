package com.juan.helpfood.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    private int id;
    private String name;
    private String description;
    private LocalTime openingHours;
    private LocalTime closingHours;
    private String address;
    private OwnerDTO owner;
}
