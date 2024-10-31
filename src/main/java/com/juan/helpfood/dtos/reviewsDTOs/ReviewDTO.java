package com.juan.helpfood.dtos.reviewsDTOs;

import com.juan.helpfood.dtos.restaurantsDTOs.RestaurantDTO;
import com.juan.helpfood.dtos.userDTOs.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Integer id;
    private UserDTO user;
    private RestaurantDTO restaurant;
    private int rating;
    private String comment;
}
