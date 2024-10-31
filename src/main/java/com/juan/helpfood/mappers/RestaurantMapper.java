package com.juan.helpfood.mappers;

import com.juan.helpfood.dtos.restaurantsDTOs.CreateRestaurantDTO;
import com.juan.helpfood.dtos.restaurantsDTOs.RestaurantDTO;
import com.juan.helpfood.entities.Restaurant;
import com.juan.helpfood.entities.User;

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
        restaurantDTO.setCelNumber(restaurant.getCelNumber());
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setOwnerId(restaurant.getOwner().getId());
        return restaurantDTO;
    }

    public static Restaurant toRestaurant(CreateRestaurantDTO createRestaurantDTO, User owner) {
        if (createRestaurantDTO == null) {
            return null;
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setName(createRestaurantDTO.getName());
        restaurant.setDescription(createRestaurantDTO.getDescription());
        restaurant.setOpeningHours(createRestaurantDTO.getOpeningHours());
        restaurant.setClosingHours(createRestaurantDTO.getClosingHours());
        restaurant.setCelNumber(createRestaurantDTO.getCelNumber());
        restaurant.setAddress(createRestaurantDTO.getAddress());
        restaurant.setOwner(owner);

        return restaurant;
    }
}
