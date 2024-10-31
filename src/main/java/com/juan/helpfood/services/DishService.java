package com.juan.helpfood.services;

import com.juan.helpfood.dtos.dishesDTOs.CreateDishDTO;
import com.juan.helpfood.dtos.dishesDTOs.DishDTO;

import java.util.List;

public interface DishService {
    public DishDTO createDish(CreateDishDTO createDishDTO);
    public DishDTO getDishById(Integer id);
    public DishDTO updateDish(Integer id,CreateDishDTO updateDishDTO);
    public void deleteDish(Integer id);
    public List<DishDTO> getDishByMenuId(Integer menuId);
}
