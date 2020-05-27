package com.mycompany.online_shop_demo_backend.service.security;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    String generateToken(String email);

    long getTokenExpiration();

    boolean validateToken(String token);

    String detachToken(HttpServletRequest request);

    String getUsernameFromToken(String token);

}
