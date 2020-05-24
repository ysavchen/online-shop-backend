package com.mycompany.online_shop_demo_backend.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("security.token")
public class JwtProperties {

    private String secretKey;
    private long expiration;

}