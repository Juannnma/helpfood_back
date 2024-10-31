package com.juan.helpfood.dtos.dishesDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDishDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer menuId;
    private List<Integer> tagsId;
}
