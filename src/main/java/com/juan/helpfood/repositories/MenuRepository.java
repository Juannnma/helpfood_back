package com.juan.helpfood.repositories;

import com.juan.helpfood.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findByRestaurantId(Integer restaurantId);
}
