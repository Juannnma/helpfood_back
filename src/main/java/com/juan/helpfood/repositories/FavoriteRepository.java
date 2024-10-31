package com.juan.helpfood.repositories;

import com.juan.helpfood.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findAllByUserId(Integer userId);
    Optional<Favorite> findAllByUserIdAndRestaurantId(Integer userId, Integer restaurantId);
}
