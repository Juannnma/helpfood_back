package com.juan.helpfood.services;

import com.juan.helpfood.dtos.favoritesDTOs.CreateFavoriteDTO;
import com.juan.helpfood.dtos.favoritesDTOs.FavoriteDTO;
import com.juan.helpfood.dtos.favoritesDTOs.FavoritesWithRestaurantDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavoriteService {
    public FavoriteDTO createFavorite(CreateFavoriteDTO createFavoriteDTO);
    public void removeFavorite(FavoriteDTO favoriteDTO);
    public List<FavoriteDTO> getFavoritesByUserId(Integer userId);
    public List<FavoritesWithRestaurantDTO> getFavoritesWhitRestaurantByUserId(Integer userId);
}
