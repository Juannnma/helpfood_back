package com.juan.helpfood.repositories;

import com.juan.helpfood.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByRestaurantId(Integer restaurantId);
}
