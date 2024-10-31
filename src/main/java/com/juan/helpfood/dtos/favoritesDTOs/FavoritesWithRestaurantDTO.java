package com.juan.helpfood.dtos.favoritesDTOs;

import com.juan.helpfood.dtos.restaurantsDTOs.RestaurantDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoritesWithRestaurantDTO {
    private Integer id;
    private Integer userId;
    private Integer restauranteId;
    private RestaurantDTO restaurant;


}
