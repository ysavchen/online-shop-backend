package com.mycompany.online_shop_demo_backend.dto.response;

import com.mycompany.online_shop_demo_backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private long id;
    private String firstName;
    private String lastName;
    private String email;

    public static UserResponse toDto(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail().getValue()
        );
    }
}
