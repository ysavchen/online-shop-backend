package com.mycompany.online_shop_demo_backend.security;

import com.mycompany.online_shop_demo_backend.domain.User;
import com.mycompany.online_shop_demo_backend.exceptions.EntityNotFoundException;
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
        User user = userDbService.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email = " + email + " is not found"));

        return org.springframework.security.core.userdetails
                .User.withUsername(email)
                .password(user.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .authorities(ROLE_USER)
                .build();
    }
}
