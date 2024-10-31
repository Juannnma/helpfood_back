package com.juan.helpfood.dtos.favoritesDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFavoriteDTO {
    private Integer userId;
    private Integer restauranteId;
}
