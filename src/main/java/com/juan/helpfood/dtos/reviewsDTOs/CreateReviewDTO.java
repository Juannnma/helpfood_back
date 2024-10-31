package com.juan.helpfood.dtos.reviewsDTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewDTO {
    private Integer userId;
    private Integer restaurantId;
    private int rating;
    private String comment;
}
