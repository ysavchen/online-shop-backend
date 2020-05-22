package com.mycompany.online_shop_demo_backend.controllers.rest;

import com.mycompany.online_shop_demo_backend.domain.User;
import com.mycompany.online_shop_demo_backend.dto.request.LoginRequest;
import com.mycompany.online_shop_demo_backend.dto.request.RegisterRequest;
import com.mycompany.online_shop_demo_backend.dto.response.LoginResponse;
import com.mycompany.online_shop_demo_backend.dto.response.RegisterResponse;
import com.mycompany.online_shop_demo_backend.dto.response.UserResponse;
import com.mycompany.online_shop_demo_backend.exceptions.EntityNotFoundException;
import com.mycompany.online_shop_demo_backend.service.db.UserDbService;
import com.mycompany.online_shop_demo_backend.service.security.SecurityService;
import io.swagger.annotations.ApiOperation;
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

    private final UserDbService dbService;
    private final SecurityService securityService;

    @ApiOperation("Registers a user")
    @PostMapping(path = "/api/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        logger.info("Register user with email {}", request.getEmail());
        User user = RegisterRequest.toDomainUser(request);

        user.setPassword(securityService.encodePassword(user.getPassword()));
        UserResponse userResponse = UserResponse.toDto(dbService.save(user));
        String token = securityService.generateToken(userResponse.getEmail());

        return new RegisterResponse(token, userResponse);
    }

    @ApiOperation("Logs in a user")
    @PostMapping(path = "/api/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse login(@RequestBody LoginRequest request) {
        logger.info("Login with email {}", request.getEmail());
        securityService.authenticate(request.getEmail(), request.getPassword());

        UserResponse userResponse = dbService.findByEmail(request.getEmail())
                .map(UserResponse::toDto)
                .orElseThrow(() -> new EntityNotFoundException("User with email = " + request.getEmail() + " is not found"));
        String token = securityService.generateToken(userResponse.getEmail());

        return new LoginResponse(token, userResponse);
    }
}
