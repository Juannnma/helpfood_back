package com.juan.helpfood.services.impl;

import com.juan.helpfood.dtos.RestaurantDTO;
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
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        // Validar si el propietario (owner) existe en la base de datos
        User owner = userRepository.findById(restaurantDTO.getOwner().getId())
                .orElseThrow(() -> new NoSuchElementException("Owner not found with id: " + restaurantDTO.getOwner().getId()));

        // Convertir el DTO a entidad
        Restaurant restaurant = RestaurantMapper.toRestaurant(restaurantDTO);

        // Asignar el owner al restaurante
        restaurant.setOwner(owner);

        // Guardar el restaurante
        restaurant = restaurantRepository.save(restaurant);

        // Devolver el DTO del restaurante guardado
        return RestaurantMapper.toRestaurantDTO(restaurant);
    }

    @Override
    public RestaurantDTO updateRestaurant(int id,RestaurantDTO restaurantDTO) {
        // Verificar si el restaurante existe
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Restaurant not found with id: " + id));

        // Si hay un cambio en el owner, buscar el nuevo propietario
        if (restaurantDTO.getOwner() != null && (restaurantDTO.getOwner().getId() != (existingRestaurant.getOwner().getId()))) {
            User newOwner = userRepository.findById(restaurantDTO.getOwner().getId())
                    .orElseThrow(() -> new NoSuchElementException("Owner not found with id: " + restaurantDTO.getOwner().getId()));
            existingRestaurant.setOwner(newOwner);
        }

        // Actualizar otros campos
        existingRestaurant.setName(restaurantDTO.getName());
        existingRestaurant.setDescription(restaurantDTO.getDescription());
        existingRestaurant.setOpeningHours(restaurantDTO.getOpeningHours());
        existingRestaurant.setClosingHours(restaurantDTO.getClosingHours());
        existingRestaurant.setAddress(restaurantDTO.getAddress());

        // Guardar los cambios
        Restaurant updatedRestaurant = restaurantRepository.save(existingRestaurant);

        // Convertir a DTO y devolver
        return RestaurantMapper.toRestaurantDTO(updatedRestaurant);
    }

    @Override
    public RestaurantDTO getRestaurantById(int id) {
        return null;
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
    public boolean deleteRestaurant(int id) {
        return false;
    }
}
