package com.mycompany.online_shop_demo_backend.controllers;

import com.google.gson.Gson;
import com.mycompany.online_shop_demo_backend.domain.User;
import com.mycompany.online_shop_demo_backend.dto.request.LoginRequest;
import com.mycompany.online_shop_demo_backend.dto.request.RegisterRequest;
import com.mycompany.online_shop_demo_backend.dto.response.AuthResponse;
import com.mycompany.online_shop_demo_backend.dto.response.UserResponse;
import com.mycompany.online_shop_demo_backend.service.db.UserDbService;
import com.mycompany.online_shop_demo_backend.service.security.SecurityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTests {

    private final String userOneEmail = "userOne@test.com";
    private final String userOnePassword = "Start01#";
    private final String userOnePasswordEncoded = "Encoded Start01#";
    private final String token = "Secret token";
    private final long tokenExpiration = 10000L;

    private final User userOne = new User(1, "Name One", "Surname One", userOneEmail, userOnePasswordEncoded);
    private final RegisterRequest registerRequest = new RegisterRequest(userOne.getFirstName(), userOne.getLastName(), userOneEmail, userOnePassword);
    private final AuthResponse authResponse = new AuthResponse(
            token, tokenExpiration,
            new UserResponse(userOne.getId(), userOne.getFirstName(), userOne.getLastName(), userOne.getEmail()));

    private final LoginRequest loginRequest = new LoginRequest(userOneEmail, userOnePassword);

    private final Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDbService dbService;

    @MockBean
    private SecurityService securityService;

    @Test
    public void register() throws Exception {
        when(securityService.encodePassword(anyString())).thenReturn("Encoded " + registerRequest.getPassword());
        when(securityService.generateToken(anyString())).thenReturn(token);
        when(securityService.getTokenExpirationInMillis()).thenReturn(tokenExpiration);
        when(dbService.save(any(User.class))).thenReturn(userOne);

        mockMvc.perform(
                post("/api/register")
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
        when(dbService.findByEmail(registerRequest.getEmail())).thenReturn(Optional.of(userOne));
        when(securityService.generateToken(userOne.getEmail())).thenReturn(token);
        when(securityService.getTokenExpirationInMillis()).thenReturn(tokenExpiration);

        mockMvc.perform(
                post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(gson.toJson(authResponse)));
    }
}
