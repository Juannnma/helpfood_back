package com.juan.helpfood.services;

import com.juan.helpfood.dtos.SignInUserDTO;
import com.juan.helpfood.dtos.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(SignInUserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);
    UserDTO getUserById(int id);
    List<UserDTO> getAllUsers();
    boolean deleteUser(int id);
}
