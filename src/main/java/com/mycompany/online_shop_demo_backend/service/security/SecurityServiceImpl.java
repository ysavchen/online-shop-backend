package com.mycompany.online_shop_demo_backend.service.security;

import com.mycompany.online_shop_demo_backend.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;

    public Authentication authenticate(String email, String password) {
        return authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    @Override
    public String generateToken(String email) {
        return jwtProvider.generateToken(email);
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
