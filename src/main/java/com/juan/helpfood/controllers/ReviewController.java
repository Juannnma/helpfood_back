package com.juan.helpfood.controllers;

import com.juan.helpfood.dtos.reviewsDTOs.CreateReviewDTO;
import com.juan.helpfood.dtos.reviewsDTOs.ReviewDTO;
import com.juan.helpfood.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByRestaurantId(@PathVariable Integer restaurantId) {
        List<ReviewDTO> reviews = reviewService.getReviewsByRestaurant(restaurantId);
        return ResponseEntity.ok(reviews);
    }
    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@RequestBody CreateReviewDTO createReviewDTO) {
        ReviewDTO reviewDTO = reviewService.createReview(createReviewDTO);
        return ResponseEntity.ok(reviewDTO);
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ReviewDTO> deleteReview(@PathVariable Integer reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }
}
