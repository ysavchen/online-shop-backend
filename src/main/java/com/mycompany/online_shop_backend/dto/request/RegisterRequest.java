package com.mycompany.online_shop_backend.dto.request;

import com.mycompany.online_shop_backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public static User toUserEntity(RegisterRequest dto) {
        return new User(0L, dto.firstName, dto.lastName, dto.email, dto.password);
    }
}
