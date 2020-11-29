package com.mycompany.online_shop_backend.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Configuration
public class AppConfig {

    private static final String SERVER_TIMEZONE = "UTC";

    @PostConstruct
    public void setTimezone() {
        TimeZone.setDefault(TimeZone.getTimeZone(SERVER_TIMEZONE));
    }
}
