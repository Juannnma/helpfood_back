package com.juan.helpfood.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInUserDTO extends UserDTO {
    private String email;
    private String username;
    private String password;
}
