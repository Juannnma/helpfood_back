package com.juan.helpfood.mappers;

import com.juan.helpfood.dtos.dishesDTOs.DishDTO;
import com.juan.helpfood.entities.Dish;
import com.juan.helpfood.entities.Tag;

public class DishMapper {
    public static DishDTO toDishDTO(Dish dish) {
        DishDTO dishDTO = new DishDTO();
        dishDTO.setId(dish.getId());
        dishDTO.setName(dish.getName());
        dishDTO.setDescription(dish.getDescription());
        dishDTO.setPrice(dish.getPrice());
        dishDTO.setMenuId(dish.getMenu().getId());
        dishDTO.setTagsId(dish.getTags().stream().map(Tag::getName).toList());
        return dishDTO;
    }
}
