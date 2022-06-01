package com.addy360.sec.configs;

import com.addy360.sec.service.RoleService;
import com.addy360.sec.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecConfiguration {
    private final RoleService roleService;
    private final UserService userService;

    @Bean
    CommandLineRunner runOnStartup(){
        return args -> {
            roleService.insertingDefaultRoles();
            userService.createSuperUser(passwordEncoder());
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }
}
