package com.juan.helpfood.services;

import com.juan.helpfood.dtos.menusDTOs.CreateMenuAndDishesDTO;
import com.juan.helpfood.dtos.menusDTOs.CreateMenuDTO;
import com.juan.helpfood.dtos.menusDTOs.MenuAndDishesDTO;
import com.juan.helpfood.dtos.menusDTOs.MenuDTO;

import java.util.List;

public interface MenuService {
    public MenuDTO createMenu(CreateMenuDTO createMenuDTO);
    public List<MenuDTO> getMenusByRestaurantId(Integer restaurantId);
    public List<MenuAndDishesDTO> createMenuAndDishes(List<CreateMenuAndDishesDTO> createMenuAndDishesDTOList, Integer restaurantId);
}
