package com.addy360.sec.configs;

import com.addy360.sec.models.User;
import com.addy360.sec.service.RoleService;
import com.addy360.sec.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecConfiguration {
    private final RoleService roleService;
    private final UserService userService;

    @Bean
    CommandLineRunner runOnStartup() {
        return args -> {
            roleService.insertingDefaultRoles();
            userService.createSuperUser(passwordEncoder());
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            User user = userService.findByEmail(username);
            if (!Optional.ofNullable(user).isPresent())
                throw new UsernameNotFoundException(username.concat(" not found"));
            List<SimpleGrantedAuthority> authorities = user.getRoles()
                    .stream().map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                    .collect(Collectors.toList());

            return new org.springframework.security.core.userdetails.User(
                    username,
                    user.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    authorities
            );
        };
    }
}
