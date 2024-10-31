package com.juan.helpfood.repositories;

import com.juan.helpfood.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    List<Dish> findDishByMenuId(Integer menuId);
}
