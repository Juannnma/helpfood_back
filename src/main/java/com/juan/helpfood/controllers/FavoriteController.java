package com.juan.helpfood.controllers;

import com.juan.helpfood.dtos.favoritesDTOs.CreateFavoriteDTO;
import com.juan.helpfood.dtos.favoritesDTOs.FavoriteDTO;
import com.juan.helpfood.dtos.favoritesDTOs.FavoritesWithRestaurantDTO;
import com.juan.helpfood.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<FavoriteDTO> createFavorite(@RequestBody CreateFavoriteDTO createFavoriteDTO) {
        FavoriteDTO favoriteDTO = favoriteService.createFavorite(createFavoriteDTO);
        return ResponseEntity.ok(favoriteDTO);
    }

    @DeleteMapping
    public ResponseEntity<FavoriteDTO> deleteFavorite(@RequestBody FavoriteDTO favoriteDTO) {
        favoriteService.removeFavorite(favoriteDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteDTO>> getFavoriteByUserId(@PathVariable Integer userId) {
        List<FavoriteDTO> favoriteDTOList = favoriteService.getFavoritesByUserId(userId);
        return ResponseEntity.ok(favoriteDTOList);
    }
    @GetMapping("/restaurants/{userId}")
    public ResponseEntity<List<FavoritesWithRestaurantDTO>> getFavoriteWithRestaurantByUserId(@PathVariable Integer userId) {
            List<FavoritesWithRestaurantDTO> favoritesWithRestaurantDTOS = favoriteService.getFavoritesWhitRestaurantByUserId(userId);
            return ResponseEntity.ok(favoritesWithRestaurantDTOS);
    }

}
