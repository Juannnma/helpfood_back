package com.juan.helpfood.controllers;

import com.juan.helpfood.dtos.menusDTOs.CreateMenuDTO;
import com.juan.helpfood.dtos.menusDTOs.MenuDTO;
import com.juan.helpfood.services.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/menus")
public class MenuController {
    private final MenuService menuService;
    public MenuController(final MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<MenuDTO> createMenu(@RequestBody CreateMenuDTO createMenuDTO) {
        MenuDTO menuDTO = menuService.createMenu(createMenuDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuDTO);
    }

    @GetMapping("restaurant/{restaurantId}")
    public ResponseEntity<List<MenuDTO>> getMenusByRestaurant(@PathVariable Integer restaurantId) {
        List<MenuDTO> menus = menuService.getMenusByRestaurantId(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(menus);
    }
}
