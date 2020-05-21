package com.mycompany.online_shop_demo_backend.service.security;

import org.springframework.security.core.Authentication;

public interface SecurityService {

    Authentication authenticate(String email, String password);

    String generateToken(String email);

    String encodePassword(String password);

}
