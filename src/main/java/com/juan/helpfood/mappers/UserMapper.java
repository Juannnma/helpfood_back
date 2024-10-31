package com.juan.helpfood.mappers;

import com.juan.helpfood.dtos.userDTOs.SignInUserDTO;
import com.juan.helpfood.dtos.userDTOs.UserDTO;
import com.juan.helpfood.entities.User;

public class UserMapper {
    public static UserDTO toUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
    public static User toUser(SignInUserDTO signInUserDTO) {
        if (signInUserDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(signInUserDTO.getId());
        user.setUsername(signInUserDTO.getUsername());
        user.setEmail(signInUserDTO.getEmail());
        user.setPassword(signInUserDTO.getPassword());
        return user;
    }
}
