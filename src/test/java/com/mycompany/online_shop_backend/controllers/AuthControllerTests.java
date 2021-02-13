package com.mycompany.online_shop_backend.controllers;

import com.google.gson.Gson;
import com.mycompany.online_shop_backend.domain.User;
import com.mycompany.online_shop_backend.dto.request.LoginRequest;
import com.mycompany.online_shop_backend.dto.request.RegisterRequest;
import com.mycompany.online_shop_backend.dto.response.AuthResponse;
import com.mycompany.online_shop_backend.dto.response.UserResponse;
import com.mycompany.online_shop_backend.security.SecurityConfiguration;
import com.mycompany.online_shop_backend.security.TokenAuthenticationFilter;
import com.mycompany.online_shop_backend.security.TokenProperties;
import com.mycompany.online_shop_backend.security.UserDetailsServiceImpl;
import com.mycompany.online_shop_backend.service.UserService;
import com.mycompany.online_shop_backend.service.security.SecurityService;
import com.mycompany.online_shop_backend.service.security.TokenService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import({TokenAuthenticationFilter.class,
        TokenProperties.class,
        TokenService.class,
        SecurityConfiguration.class,
        UserDetailsServiceImpl.class})
public class AuthControllerTests {

    private final String userOneEmail = "userOne@test.com";
    private final String userOnePassword = "Start01#";
    private final String userOnePasswordEncoded = "Encoded Start01#";
    private final String token = "Secret token";
    private final long tokenExpiration = 10000L;

    private final User userOne = new User(1, "Name One", "Surname One", userOneEmail, userOnePasswordEncoded);
    private final RegisterRequest registerRequest = new RegisterRequest(
            userOne.getFirstName(),
            userOne.getLastName(),
            userOneEmail,
            userOnePassword
    );
    private final AuthResponse authResponse = new AuthResponse(
            token, tokenExpiration,
            new UserResponse(userOne.getId(), userOne.getFirstName(), userOne.getLastName(), userOne.getEmail())
    );

    private final LoginRequest loginRequest = new LoginRequest(userOneEmail, userOnePassword);

    private final Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private SecurityService securityService;

    @MockBean
    private TokenService tokenService;

    @Test
    public void register() throws Exception {
        when(securityService.encodePassword(anyString())).thenReturn("Encoded " + registerRequest.getPassword());
        when(tokenService.generateToken(anyString())).thenReturn(token);
        when(tokenService.getTokenExpiration()).thenReturn(tokenExpiration);
        when(userService.save(any(User.class))).thenReturn(userOne);

        mockMvc.perform(
                post("/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(registerRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(gson.toJson(authResponse)));
    }

    @Test
    public void login() throws Exception {
        when(securityService.authenticate(anyString(), anyString()))
                .thenReturn(new UsernamePasswordAuthenticationToken(userOneEmail, userOnePassword));
        when(userService.findByEmail(registerRequest.getEmail())).thenReturn(Optional.of(userOne));
        when(tokenService.generateToken(userOne.getEmail())).thenReturn(token);
        when(tokenService.getTokenExpiration()).thenReturn(tokenExpiration);

        mockMvc.perform(
                post("/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(gson.toJson(authResponse)));
    }

    @Disabled
    @Test
    public void loginWithFailedAuthentication() throws Exception {
        when(securityService.authenticate(anyString(), anyString())).thenThrow(new UsernameNotFoundException("User not found"));

        mockMvc.perform(
                post("/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(loginRequest)))
                .andExpect(status().isUnauthorized());
    }
}
