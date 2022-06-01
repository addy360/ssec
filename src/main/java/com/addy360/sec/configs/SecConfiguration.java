package com.addy360.sec.configs;

import com.addy360.sec.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecConfiguration {
    private final RoleService roleService;

    @Bean
    CommandLineRunner runOnStartup(){
        return args -> {
            roleService.insertingDefaultRoles();
        };
    }
}
