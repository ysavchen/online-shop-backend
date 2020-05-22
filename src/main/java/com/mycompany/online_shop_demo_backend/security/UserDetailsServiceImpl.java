package com.mycompany.online_shop_demo_backend.security;

import com.mycompany.online_shop_demo_backend.domain.User;
import com.mycompany.online_shop_demo_backend.service.db.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String ROLE_USER = "ROLE_USER";
    private final UserDbService userDbService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String password = userDbService.findByEmail(email)
                .map(User::getPassword)
                .orElseThrow(() -> new UsernameNotFoundException("User with email = " + email + " is not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(email)
                .password(password)
                .authorities(ROLE_USER)
                .build();
    }
}
