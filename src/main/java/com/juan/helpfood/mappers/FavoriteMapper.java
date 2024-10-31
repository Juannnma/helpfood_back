package com.juan.helpfood.mappers;

import com.juan.helpfood.dtos.favoritesDTOs.FavoriteDTO;
import com.juan.helpfood.entities.Favorite;

public class FavoriteMapper {
    public static FavoriteDTO toFavoriteDTO(Favorite favorite) {
        FavoriteDTO favoriteDTO = new FavoriteDTO();
        favoriteDTO.setId(favorite.getId());
        favoriteDTO.setUserId(favorite.getUser().getId());
        favoriteDTO.setRestauranteId(favorite.getRestaurant().getId());

        return favoriteDTO;
    }
}
