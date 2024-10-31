package com.juan.helpfood.services.impl;

import com.juan.helpfood.dtos.dishesDTOs.DishDTO;
import com.juan.helpfood.dtos.menusDTOs.MenuAndDishesDTO;
import com.juan.helpfood.dtos.restaurantsDTOs.CreateRestaurantDTO;
import com.juan.helpfood.dtos.restaurantsDTOs.RestaurantDTO;
import com.juan.helpfood.dtos.restaurantsDTOs.RestaurantWithMenusDTO;
import com.juan.helpfood.entities.Restaurant;
import com.juan.helpfood.entities.User;
import com.juan.helpfood.mappers.RestaurantMapper;
import com.juan.helpfood.repositories.RestaurantRepository;
import com.juan.helpfood.repositories.UserRepository;
import com.juan.helpfood.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository,UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RestaurantDTO createRestaurant(CreateRestaurantDTO createRestaurantDTO) {
        User owner = userRepository.findById(createRestaurantDTO.getOwnerId())
                .orElseThrow(() -> new NoSuchElementException("Owner not found with id: " + createRestaurantDTO.getOwnerId()));

        Restaurant restaurant = RestaurantMapper.toRestaurant(createRestaurantDTO,owner);

        restaurant = restaurantRepository.save(restaurant);

        return RestaurantMapper.toRestaurantDTO(restaurant);
    }

    @Override
    public RestaurantDTO updateRestaurant(Integer id,RestaurantDTO restaurantDTO) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Restaurant not found with id: " + id));

        if (restaurantDTO.getOwnerId() != null && (!restaurantDTO.getOwnerId().equals(existingRestaurant.getOwner().getId()))) {
            User newOwner = userRepository.findById(restaurantDTO.getOwnerId())
                    .orElseThrow(() -> new NoSuchElementException("Owner not found with id: " + restaurantDTO.getOwnerId()));
            existingRestaurant.setOwner(newOwner);
        }

        existingRestaurant.setName(restaurantDTO.getName());
        existingRestaurant.setDescription(restaurantDTO.getDescription());
        existingRestaurant.setOpeningHours(restaurantDTO.getOpeningHours());
        existingRestaurant.setClosingHours(restaurantDTO.getClosingHours());
        existingRestaurant.setAddress(restaurantDTO.getAddress());

        Restaurant updatedRestaurant = restaurantRepository.save(existingRestaurant);


        return RestaurantMapper.toRestaurantDTO(updatedRestaurant);
    }

    @Override
    public RestaurantDTO getRestaurantById(Integer id) {
        Restaurant findedRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Restaurant not found with id: " + id));
        return RestaurantMapper.toRestaurantDTO(findedRestaurant);
    }

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        // Convertir entidades a DTOs
        return restaurants.stream()
                .map(RestaurantMapper::toRestaurantDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteRestaurant(Integer id) {
        return false;
    }

    @Override
    public RestaurantWithMenusDTO getRestaurantWithMenusById(Integer id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Restaurant not found"));

        RestaurantWithMenusDTO restaurantWithMenusDTO = new RestaurantWithMenusDTO();
        restaurantWithMenusDTO.setId(restaurant.getId());
        restaurantWithMenusDTO.setName(restaurant.getName());
        restaurantWithMenusDTO.setDescription(restaurant.getDescription());
        restaurantWithMenusDTO.setOpeningHours(restaurant.getOpeningHours());
        restaurantWithMenusDTO.setClosingHours(restaurant.getClosingHours());
        restaurantWithMenusDTO.setAddress(restaurant.getAddress());

        List<MenuAndDishesDTO> menuAndDishesDTOS = restaurant.getMenuList().stream().map(
                        menu -> {
                            MenuAndDishesDTO menuAndDishesDTO = new MenuAndDishesDTO();
                            menuAndDishesDTO.setId(menu.getId());
                            menuAndDishesDTO.setName(menu.getName());

                            List<DishDTO> dishDTOS = menu.getDishes().stream().map(
                                    dish -> {
                                        DishDTO dishDTO = new DishDTO();
                                        dishDTO.setId(dish.getId());
                                        dishDTO.setName(dish.getName());
                                        dishDTO.setDescription(dish.getDescription());
                                        dishDTO.setPrice(dish.getPrice());
                                        return dishDTO;
                                    }
                            ).toList();

                            menuAndDishesDTO.setDishes(dishDTOS);
                            return menuAndDishesDTO;
                        }
                ).toList();

        restaurantWithMenusDTO.setMenus(menuAndDishesDTOS);
        return restaurantWithMenusDTO;
    }
    @Override
    public List<RestaurantDTO> getRestaurantByDishTags(List<Integer> tagsIds){
        List<Restaurant> restaurants = restaurantRepository.findRestaurantsByDishTags(tagsIds,tagsIds.size());
        return restaurants.stream()
                .map(RestaurantMapper::toRestaurantDTO).toList();
    }

}
