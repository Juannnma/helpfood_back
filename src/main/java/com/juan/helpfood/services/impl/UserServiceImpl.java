package com.juan.helpfood.services.impl;

import com.juan.helpfood.dtos.SignInUserDTO;
import com.juan.helpfood.dtos.UserDTO;
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
    public UserDTO createUser(SignInUserDTO userDTO) {
        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }

        User user = UserMapper.toUser(userDTO);

        if(userDTO.getPassword().length() < 8){
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }

        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user = userRepository.save(user);

        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User existingUser = userRepository.findById(userDTO.getId())
                .orElseThrow(()->new NoSuchElementException("User not found with id: " + userDTO.getId()));

        existingUser.setName(userDTO.getName());

        if(!existingUser.getEmail().equals(userDTO.getEmail())){
            if(userRepository.existsByEmail(userDTO.getEmail())){
                throw new IllegalArgumentException("Email already exists");
            }
        }
        if(userDTO.getPassword() != null && userDTO.getPassword().length() >= 8){
            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        existingUser.setPassword(userDTO.getPassword());

        User updatedUser = userRepository.save(existingUser);

        return UserMapper.toUserDto(updatedUser);
    }

    @Override
    public UserDTO getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("User not found with id: " + id));
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
