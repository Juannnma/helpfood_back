package com.juan.helpfood.services;

import com.juan.helpfood.dtos.userDTOs.SignInUserDTO;
import com.juan.helpfood.dtos.userDTOs.UpdatePasswordDTO;
import com.juan.helpfood.dtos.userDTOs.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(SignInUserDTO userDTO);
    UserDTO updateUserProfile(UserDTO userDTO);
    boolean updatePassword(int userId, UpdatePasswordDTO updatePasswordDTO);
    UserDTO getUserById(int id);
    List<UserDTO> getAllUsers();
    boolean deleteUser(int id);
}
