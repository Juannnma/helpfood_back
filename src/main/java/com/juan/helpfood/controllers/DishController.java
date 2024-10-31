package com.juan.helpfood.controllers;

import com.juan.helpfood.dtos.dishesDTOs.CreateDishDTO;
import com.juan.helpfood.dtos.dishesDTOs.DishDTO;
import com.juan.helpfood.services.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dishes")
public class DishController {

    private DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping
    public ResponseEntity<DishDTO> createDish(@RequestBody CreateDishDTO createDishDTO) {
        DishDTO dishDTO = dishService.createDish(createDishDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dishDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDTO> getDishById(@PathVariable Integer id) {
        DishDTO dishDTO = dishService.getDishById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dishDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishDTO> updateDish(@PathVariable Integer id,@RequestBody CreateDishDTO updateDishDTO) {
        DishDTO dishDTO = dishService.updateDish(id, updateDishDTO);
        return ResponseEntity.status(HttpStatus.OK).body(dishDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DishDTO> deleteDish(@PathVariable Integer id) {
        dishService.deleteDish(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<List<DishDTO>> getDishByMenuId(@PathVariable Integer menuId) {
        List<DishDTO> dishDTOList = dishService.getDishByMenuId(menuId);
        return ResponseEntity.status(HttpStatus.OK).body(dishDTOList);
    }


}
