package com.mycompany.online_shop_backend.controllers;

import com.mycompany.online_shop_backend.domain.User;
import com.mycompany.online_shop_backend.dto.request.LoginRequest;
import com.mycompany.online_shop_backend.dto.request.RegisterRequest;
import com.mycompany.online_shop_backend.dto.response.AuthResponse;
import com.mycompany.online_shop_backend.dto.response.UserResponse;
import com.mycompany.online_shop_backend.exceptions.EntityNotFoundException;
import com.mycompany.online_shop_backend.service.UserService;
import com.mycompany.online_shop_backend.service.security.SecurityService;
import com.mycompany.online_shop_backend.service.security.TokenService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final SecurityService securityService;
    private final TokenService tokenService;

    @ApiOperation("Registers a user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful registration")
    })
    @PostMapping(path = "/v1/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@RequestBody RegisterRequest request) {
        logger.info("Register user with email {}", request.getEmail());
        User user = RegisterRequest.toUserEntity(request);

        user.setPassword(securityService.encodePassword(user.getPassword()));
        UserResponse userResponse = UserResponse.toDto(userService.save(user));
        String token = tokenService.generateToken(userResponse.getEmail());
        long tokenExpiration = tokenService.getTokenExpiration();

        return new AuthResponse(token, tokenExpiration, userResponse);
    }

    @ApiOperation("Logs in a user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful login"),
            @ApiResponse(code = 404, message = "Resource not found")
    })
    @PostMapping(path = "/v1/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthResponse login(@RequestBody LoginRequest request) {
        logger.info("Login with email {}", request.getEmail());
        securityService.authenticate(request.getEmail(), request.getPassword());

        UserResponse userResponse = userService.findByEmail(request.getEmail())
                .map(UserResponse::toDto)
                .orElseThrow(() -> new EntityNotFoundException("User with email = " + request.getEmail() + " is not found"));
        String token = tokenService.generateToken(userResponse.getEmail());
        long tokenExpiration = tokenService.getTokenExpiration();

        return new AuthResponse(token, tokenExpiration, userResponse);
    }
}
