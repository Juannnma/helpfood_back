package com.juan.helpfood.services.impl;

import com.juan.helpfood.dtos.dishesDTOs.CreateDishDTO;
import com.juan.helpfood.dtos.dishesDTOs.DishDTO;
import com.juan.helpfood.entities.Dish;
import com.juan.helpfood.entities.Menu;
import com.juan.helpfood.entities.Tag;
import com.juan.helpfood.mappers.DishMapper;
import com.juan.helpfood.repositories.DishRepository;
import com.juan.helpfood.repositories.MenuRepository;
import com.juan.helpfood.repositories.TagRepository;
import com.juan.helpfood.services.DishService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final MenuRepository menuRepository;
    private final DishRepository dishRepository;
    private final TagRepository tagRepository;

    public DishServiceImpl(MenuRepository menuRepository, DishRepository dishRepository, TagRepository tagRepository) {
        this.menuRepository = menuRepository;
        this.dishRepository = dishRepository;
        this.tagRepository = tagRepository;
    }
    @Override
    public DishDTO createDish(CreateDishDTO createDishDTO) {
        Menu menu = menuRepository.findById(createDishDTO.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        Dish dish = new Dish();
        dish.setName(createDishDTO.getName());
        dish.setDescription(createDishDTO.getDescription());
        dish.setPrice(createDishDTO.getPrice());
        dish.setMenu(menu);

        List<Tag> tags = tagRepository.findAllById(createDishDTO.getTagsId());
        dish.setTags(new HashSet<>(tags));

        dishRepository.save(dish);
        return DishMapper.toDishDTO(dish);
    }

    @Override
    public DishDTO getDishById(Integer id) {
        Dish dish = dishRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Dish not found")
        );
        return DishMapper.toDishDTO(dish);
    }

    @Override
    public DishDTO updateDish(Integer id, CreateDishDTO updateDishDTO) {
        Dish updatedDish = dishRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Dish not found")
        );
        if(updateDishDTO.getMenuId() != null) {
            Menu menu = menuRepository.findById(updateDishDTO.getMenuId()).orElseThrow(
                    () -> new RuntimeException("Menu not found")
            );
            updatedDish.setMenu(menu);
        }
        updatedDish.setName(updateDishDTO.getName());
        updatedDish.setDescription(updateDishDTO.getDescription());
        updatedDish.setPrice(updateDishDTO.getPrice());

        dishRepository.save(updatedDish);
        return DishMapper.toDishDTO(updatedDish);
    }

    @Override
    public void deleteDish(Integer id) {
        dishRepository.deleteById(id);
    }

    @Override
    public List<DishDTO> getDishByMenuId(Integer menuId) {
        return dishRepository.findDishByMenuId(menuId)
                .stream()
                .map(DishMapper::toDishDTO)
                .toList();
    }
}
