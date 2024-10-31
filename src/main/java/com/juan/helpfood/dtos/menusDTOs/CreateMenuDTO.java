package com.juan.helpfood.dtos.menusDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMenuDTO {
    private String name;
    private Integer restaurantId;
}

