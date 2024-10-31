package com.juan.helpfood.controllers;

import com.juan.helpfood.dtos.restaurantsDTOs.CreateRestaurantDTO;
import com.juan.helpfood.dtos.restaurantsDTOs.RestaurantDTO;
import com.juan.helpfood.dtos.restaurantsDTOs.RestaurantWithMenusDTO;
import com.juan.helpfood.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody CreateRestaurantDTO createRestaurantDTO) {
        RestaurantDTO restaurantDTO = restaurantService.createRestaurant(createRestaurantDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantDTO);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }
    @GetMapping("/{restaurantId}/menu")
    public ResponseEntity<RestaurantWithMenusDTO> getRestaurantWithMenu(@PathVariable Integer restaurantId) {
        RestaurantWithMenusDTO restaurant = restaurantService.getRestaurantWithMenusById(restaurantId);
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping("/searchByTags")
    public ResponseEntity<List<RestaurantDTO>> getRestaurantsByTags(@RequestParam(value = "tags", required = false) List<Integer> tags) {
        List<RestaurantDTO> restaurantDTOS = restaurantService.getRestaurantByDishTags(tags);
        return ResponseEntity.ok(restaurantDTOS);
    }
}
