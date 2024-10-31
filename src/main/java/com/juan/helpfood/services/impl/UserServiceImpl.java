package com.juan.helpfood.services.impl;

import com.juan.helpfood.dtos.userDTOs.SignInUserDTO;
import com.juan.helpfood.dtos.userDTOs.UpdatePasswordDTO;
import com.juan.helpfood.dtos.userDTOs.UserDTO;
import com.juan.helpfood.entities.User;
import com.juan.helpfood.mappers.UserMapper;
import com.juan.helpfood.repositories.UserRepository;
import com.juan.helpfood.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO createUser(SignInUserDTO signInUserDTO) {
        if(userRepository.existsByEmail(signInUserDTO.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }

        User user = UserMapper.toUser(signInUserDTO);

        if(signInUserDTO.getPassword().length() < 8){
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }

        user.setPassword(passwordEncoder.encode(signInUserDTO.getPassword()));
        user = userRepository.save(user);

        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDTO updateUserProfile(UserDTO userDTO) {
        User existingUser = userRepository.findById(userDTO.getId())
                .orElseThrow(()->new NoSuchElementException("User not found with id: " + userDTO.getId()));

        existingUser.setUsername(userDTO.getUsername());

        if(!existingUser.getEmail().equals(userDTO.getEmail())){
            if(userRepository.existsByEmail(userDTO.getEmail())){
                throw new IllegalArgumentException("Email already exists");
            }
            existingUser.setEmail(userDTO.getEmail());
        }

        User updatedUser = userRepository.save(existingUser);

        return UserMapper.toUserDto(updatedUser);
    }

    @Override
    public boolean updatePassword(int userId, UpdatePasswordDTO updatePasswordDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(()->new NoSuchElementException("User not found with id: " + userId));
        if (!passwordEncoder.matches(updatePasswordDTO.getCurrentPassword(), existingUser.getPassword())){
            throw new IllegalArgumentException("Current password does not match");
        }
        if (!passwordEncoder.matches(updatePasswordDTO.getNewPassword(), existingUser.getPassword())){
            throw new IllegalArgumentException("New password does not match");
        }

        existingUser.setPassword(passwordEncoder.encode(updatePasswordDTO.getNewPassword()));
        userRepository.save(existingUser);

        return true;
    }

    @Override
    public UserDTO getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("User not found with id: " + id));
        return UserMapper.toUserDto(user);
    }
    @Override
    public UserDTO getUserByEmail(String mail){
        User user = userRepository.findByEmail(mail)
                .orElseThrow(()->new NoSuchElementException("User not found with email: " + mail));
        return UserMapper.toUserDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteUser(int id) {
        if(!userRepository.existsById(id)){
            return false;
        }

        userRepository.deleteById(id);
        return true;
    }
}
