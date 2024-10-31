package com.juan.helpfood.services.impl;

import com.juan.helpfood.dtos.dishesDTOs.CreateDishDTO;
import com.juan.helpfood.dtos.dishesDTOs.DishDTO;
import com.juan.helpfood.dtos.menusDTOs.CreateMenuAndDishesDTO;
import com.juan.helpfood.dtos.menusDTOs.CreateMenuDTO;
import com.juan.helpfood.dtos.menusDTOs.MenuAndDishesDTO;
import com.juan.helpfood.dtos.menusDTOs.MenuDTO;
import com.juan.helpfood.entities.Dish;
import com.juan.helpfood.entities.Menu;
import com.juan.helpfood.entities.Restaurant;
import com.juan.helpfood.entities.Tag;
import com.juan.helpfood.mappers.DishMapper;
import com.juan.helpfood.mappers.MenuMapper;
import com.juan.helpfood.repositories.DishRepository;
import com.juan.helpfood.repositories.MenuRepository;
import com.juan.helpfood.repositories.RestaurantRepository;
import com.juan.helpfood.repositories.TagRepository;
import com.juan.helpfood.services.MenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final TagRepository tagRepository;
    private final DishRepository dishRepository;

    public MenuServiceImpl(MenuRepository menuRepository, RestaurantRepository restaurantRepository, TagRepository tagRepository, DishRepository dishRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
        this.tagRepository = tagRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public MenuDTO createMenu(CreateMenuDTO createMenuDTO) {
        Restaurant restaurant = restaurantRepository.findById(createMenuDTO.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with id: " + createMenuDTO.getRestaurantId()));

        Menu menu = new Menu();
        menu.setName(createMenuDTO.getName());
        menu.setRestaurant(restaurant);

        menuRepository.save(menu);
        return MenuMapper.toMenuDTO(menu);
    }

    @Override
    public List<MenuDTO> getMenusByRestaurantId(Integer restaurantId) {
        List<Menu> menus = menuRepository.findByRestaurantId(restaurantId);
        return menus.stream().map(MenuMapper::toMenuDTO).collect(Collectors.toList());
    }
    @Override
    public List<MenuAndDishesDTO> createMenuAndDishes(List<CreateMenuAndDishesDTO> createMenuAndDishesDTOList, Integer restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with id: " + restaurantId));

        List<MenuAndDishesDTO> menuAndDishesDTOs = new ArrayList<>();

        for (CreateMenuAndDishesDTO createMenuAndDishesDTO : createMenuAndDishesDTOList) {

            Menu menu = new Menu();
            menu.setRestaurant(restaurant);
            menu.setName(createMenuAndDishesDTO.getMenuName());
            menu = menuRepository.save(menu);

            List<DishDTO> dishDTOs = new ArrayList<>();

            for (CreateDishDTO dishDto : createMenuAndDishesDTO.getDishes()) {
                Dish dish = new Dish();
                dish.setName(dishDto.getName());
                dish.setDescription(dishDto.getDescription());
                dish.setPrice(dishDto.getPrice());
                dish.setMenu(menu);

                List<Tag> tags = tagRepository.findAllById(dishDto.getTagsId());
                dish.setTags(new HashSet<>(tags));

                dish = dishRepository.save(dish);


                dishDTOs.add(DishMapper.toDishDTO(dish));
            }

            MenuAndDishesDTO menuAndDishesDTO = new MenuAndDishesDTO();
            menuAndDishesDTO.setId(menu.getId());
            menuAndDishesDTO.setName(menu.getName());
            menuAndDishesDTO.setDishes(dishDTOs);

            menuAndDishesDTOs.add(menuAndDishesDTO);
        }

        return menuAndDishesDTOs;
    }

}
