package com.mycompany.online_shop_backend.service;

import com.mycompany.online_shop_backend.domain.User;

import java.util.Optional;

public interface UserService {

    User save(User order);

    Optional<User> findByEmail(String email);

}
