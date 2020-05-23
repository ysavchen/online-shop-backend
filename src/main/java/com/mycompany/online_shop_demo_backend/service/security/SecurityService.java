package com.mycompany.online_shop_demo_backend.service.security;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {

    Authentication authenticate(String email, String password);

    String getUsernameFromRequest(HttpServletRequest request);

    String generateToken(String email);

    long getTokenExpirationInMillis();

    String encodePassword(String password);

}
