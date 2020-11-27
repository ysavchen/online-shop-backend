package com.mycompany.online_shop_backend.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final TokenService tokenService;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(String email, String password) {
        return authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public String getUsernameFromRequest(HttpServletRequest request) {
        String token = tokenService.detachToken(request);
        return tokenService.getUsernameFromToken(token);
    }
}
