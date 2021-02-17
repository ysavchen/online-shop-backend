package com.mycompany.online_shop_backend.dto.response;

import com.mycompany.online_shop_backend.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private long tokenExpiration;
    private UserDto user;

}
