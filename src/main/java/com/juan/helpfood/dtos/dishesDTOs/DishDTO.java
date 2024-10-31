package com.juan.helpfood.dtos.dishesDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDTO {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer menuId;
}
