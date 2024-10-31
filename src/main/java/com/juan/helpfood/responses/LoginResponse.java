package com.juan.helpfood.responses;


import com.juan.helpfood.dtos.userDTOs.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private Date expireWhen;
    private UserDTO userData;
}
