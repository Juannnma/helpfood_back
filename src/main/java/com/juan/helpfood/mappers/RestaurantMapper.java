package com.juan.helpfood.mappers;

import com.juan.helpfood.dtos.RestaurantDTO;
import com.juan.helpfood.entities.Restaurant;

public class RestaurantMapper {
    public static RestaurantDTO toRestaurantDTO(Restaurant restaurant) {
        if (restaurant == null) {
            return null;
        }
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setDescription(restaurant.getDescription());
        restaurantDTO.setOpeningHours(restaurant.getOpeningHours());
        restaurantDTO.setClosingHours(restaurant.getClosingHours());
        restaurantDTO.setAddress(restaurant.getAddress());
        return restaurantDTO;
    }

    public static Restaurant toRestaurant(RestaurantDTO restaurantDTO) {
        if (restaurantDTO == null) {
            return null;
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDTO.getId());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setDescription(restaurantDTO.getDescription());
        restaurant.setOpeningHours(restaurantDTO.getOpeningHours());
        restaurant.setClosingHours(restaurantDTO.getClosingHours());
        restaurant.setAddress(restaurantDTO.getAddress());

        return restaurant;
    }
}
