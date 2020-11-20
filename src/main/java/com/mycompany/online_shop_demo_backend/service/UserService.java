package com.mycompany.online_shop_demo_backend.service;

import com.mycompany.online_shop_demo_backend.domain.User;

import java.util.Optional;

public interface UserService {

    User save(User order);

    Optional<User> findByEmail(String email);

}
