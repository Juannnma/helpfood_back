package com.juan.helpfood.services.impl;

import com.juan.helpfood.dtos.reviewsDTOs.CreateReviewDTO;
import com.juan.helpfood.dtos.reviewsDTOs.ReviewDTO;
import com.juan.helpfood.entities.Restaurant;
import com.juan.helpfood.entities.Review;
import com.juan.helpfood.entities.User;
import com.juan.helpfood.mappers.ReviewMapper;
import com.juan.helpfood.repositories.RestaurantRepository;
import com.juan.helpfood.repositories.ReviewRepository;
import com.juan.helpfood.repositories.UserRepository;
import com.juan.helpfood.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository,RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public ReviewDTO createReview(CreateReviewDTO createReviewDTO) {
        User user = userRepository.findById(createReviewDTO.getUserId()).orElseThrow(()-> new RuntimeException("User Not Found"));
        Restaurant restaurant = restaurantRepository.findById(createReviewDTO.getRestaurantId()).orElseThrow(()-> new RuntimeException("Restaurant Not Found"));
        Review review = new Review();
        review.setUser(user);
        review.setRestaurant(restaurant);
        review.setRating(createReviewDTO.getRating());
        review.setComment(createReviewDTO.getComment());
        review.setCreatedAt(LocalDateTime.now());

        return ReviewMapper.toReviewDTO(reviewRepository.save(review));
    }

    @Override
    public ReviewDTO updateReview(ReviewDTO reviewDTO) {
        return null;
    }

    @Override
    public List<ReviewDTO> getReviewsByRestaurant(Integer restaurantId) {
        List<Review> reviews = reviewRepository.findByRestaurantId(restaurantId);
        return reviews.stream().map(ReviewMapper::toReviewDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteReview(Integer reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
