package com.juan.helpfood.dtos.userDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO {
    private String name;
    private String email;
    private String password;
}
