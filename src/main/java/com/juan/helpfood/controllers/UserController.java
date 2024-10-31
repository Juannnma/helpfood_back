package com.juan.helpfood.controllers;

import com.juan.helpfood.dtos.SignInUserDTO;
import com.juan.helpfood.dtos.UserDTO;
import com.juan.helpfood.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody SignInUserDTO userDTO) {
        UserDTO creadoRestaurante = userService.createUser(userDTO);
        return new ResponseEntity<>(creadoRestaurante, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        UserDTO restauranteDTO = userService.getUserById(id);
        return new ResponseEntity<>(restauranteDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> restaurantes = userService.getAllUsers();
        return new ResponseEntity<>(restaurantes, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<UserDTO> deleteUserById(@RequestParam int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
