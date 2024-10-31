package com.juan.helpfood.services;

import com.juan.helpfood.dtos.reviewsDTOs.CreateReviewDTO;
import com.juan.helpfood.dtos.reviewsDTOs.ReviewDTO;

import java.util.List;

public interface ReviewService {
    public ReviewDTO createReview(CreateReviewDTO createReviewDTO);
    public ReviewDTO updateReview(ReviewDTO reviewDTO);
    public List<ReviewDTO> getReviewsByRestaurant(Integer restaurantId);
    public void deleteReview(Integer reviewId);
}
