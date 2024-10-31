package com.juan.helpfood.mappers;

import com.juan.helpfood.dtos.restaurantsDTOs.RestaurantDTO;
import com.juan.helpfood.dtos.reviewsDTOs.ReviewDTO;
import com.juan.helpfood.dtos.userDTOs.UserDTO;
import com.juan.helpfood.entities.Review;

public class ReviewMapper {
    public static ReviewDTO toReviewDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setComment(review.getComment());
        reviewDTO.setRating(review.getRating());

        UserDTO userDTO = UserMapper.toUserDto(review.getUser());
        reviewDTO.setUser(userDTO);
        RestaurantDTO restaurantDTO = RestaurantMapper.toRestaurantDTO(review.getRestaurant());
        reviewDTO.setRestaurant(restaurantDTO);

        return reviewDTO;
    }
}
