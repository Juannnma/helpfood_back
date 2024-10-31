package com.juan.helpfood.services.impl;

import com.juan.helpfood.dtos.favoritesDTOs.CreateFavoriteDTO;
import com.juan.helpfood.dtos.favoritesDTOs.FavoriteDTO;
import com.juan.helpfood.dtos.favoritesDTOs.FavoritesWithRestaurantDTO;
import com.juan.helpfood.entities.Favorite;
import com.juan.helpfood.entities.Restaurant;
import com.juan.helpfood.entities.User;
import com.juan.helpfood.mappers.FavoriteMapper;
import com.juan.helpfood.mappers.RestaurantMapper;
import com.juan.helpfood.repositories.FavoriteRepository;
import com.juan.helpfood.repositories.RestaurantRepository;
import com.juan.helpfood.repositories.UserRepository;
import com.juan.helpfood.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteRepository favoriteRepository;
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;

    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.favoriteRepository = favoriteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @Override
    public FavoriteDTO createFavorite(CreateFavoriteDTO createFavoriteDTO) {
        User user = userRepository.findById(createFavoriteDTO.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found"));
        Restaurant restaurant = restaurantRepository.findById(createFavoriteDTO.getRestauranteId())
                .orElseThrow(()-> new RuntimeException("Restaurant not found"));

        if (favoriteRepository.findAllByUserIdAndRestaurantId(user.getId(),restaurant.getId()).isPresent()) {
            throw new RuntimeException("Favorite already exists");
        }

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setRestaurant(restaurant);
        favoriteRepository.save(favorite);

        return FavoriteMapper.toFavoriteDTO(favorite);
    }

    @Override
    public void removeFavorite(FavoriteDTO favoriteDTO) {
        Favorite favorite = favoriteRepository.findAllByUserIdAndRestaurantId(
                favoriteDTO.getUserId(), favoriteDTO.getRestauranteId())
                .orElseThrow(()-> new RuntimeException("Favorite not found"));
        favoriteRepository.delete(favorite);
    }

    @Override
    public List<FavoriteDTO> getFavoritesByUserId(Integer userId) {
        List<Favorite> favorites = favoriteRepository.findAllByUserId(userId);
        return favorites.stream().map(FavoriteMapper::toFavoriteDTO).toList();
    }
    @Override
    public List<FavoritesWithRestaurantDTO> getFavoritesWhitRestaurantByUserId(Integer userId) {
        List<Favorite> favorites = favoriteRepository.findAllByUserId(userId);
        List<FavoritesWithRestaurantDTO> favoritesWithRestaurantDTOS = favorites.stream().map(
                favorite -> {
                    FavoritesWithRestaurantDTO favoriteWithRestaurantDTO = new FavoritesWithRestaurantDTO();
                    favoriteWithRestaurantDTO.setId(favorite.getId());
                    favoriteWithRestaurantDTO.setUserId(favorite.getUser().getId());
                    favoriteWithRestaurantDTO.setRestauranteId(favorite.getRestaurant().getId());
                    Restaurant restaurant = restaurantRepository.findById(favorite.getRestaurant().getId())
                            .orElseThrow(()-> new RuntimeException("Restaurant not found"));
                    favoriteWithRestaurantDTO.setRestaurant(RestaurantMapper.toRestaurantDTO(restaurant));
                    return favoriteWithRestaurantDTO;

                }
        ).toList();
        return favoritesWithRestaurantDTOS;


    }
}
