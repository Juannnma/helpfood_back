package com.juan.helpfood.services;

import com.juan.helpfood.dtos.RestaurantDTO;

import java.util.List;

public interface RestaurantService {
    RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO);
    RestaurantDTO updateRestaurant(int id,RestaurantDTO restaurantDTO);
    RestaurantDTO getRestaurantById(int id);
    List<RestaurantDTO> getAllRestaurants();
    boolean deleteRestaurant(int id);
}
