package com.juan.helpfood.controllers;

import com.juan.helpfood.dtos.userDTOs.LoginUserDTO;
import com.juan.helpfood.dtos.userDTOs.SignInUserDTO;
import com.juan.helpfood.services.JwtService;
import com.juan.helpfood.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin; // Importación de CrossOrigin
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:8081") // Permite solicitudes desde el frontend en localhost:8081
public class AuthenticationController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, final JwtService jwtService, final UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginUserDTO loginUserDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserDTO.getEmail(), loginUserDTO.getPassword())
        );
        String jwt = jwtService.generateToken((UserDetails) authentication.getPrincipal());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> registerUser(@RequestBody SignInUserDTO signInUserDTO) {
        userService.createUser(signInUserDTO);
        return ResponseEntity.ok("User registered successfully!");
    }
}
