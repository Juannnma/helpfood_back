package com.juan.helpfood.mappers;

import com.juan.helpfood.dtos.menusDTOs.MenuDTO;
import com.juan.helpfood.entities.Menu;

public class MenuMapper {
    public static MenuDTO toMenuDTO(Menu menu) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(menu.getId());
        menuDTO.setName(menu.getName());
        menuDTO.setRestaurantId(menu.getRestaurant().getId());
        return menuDTO;
    }
}
