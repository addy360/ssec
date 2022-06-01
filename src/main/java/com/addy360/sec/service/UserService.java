package com.addy360.sec.service;

import com.addy360.sec.dto.UserDto;
import com.addy360.sec.enums.RoleEnum;
import com.addy360.sec.models.User;
import com.addy360.sec.repository.RoleRepository;
import com.addy360.sec.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void createSuperUser(PasswordEncoder passwordEncoder){

        User user = new User();
        user.setLastName("John");
        user.setFirstName("Doe");
        user.setEmail("admin@sec.com");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRoles(Collections.singletonList(roleRepository.findByRoleName(RoleEnum.ADMIN.getRoleName())));
        userRepository.save(user);
        log.info("Created super user {}",user.getEmail());
    }

    public User createUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setPassword(user.getPassword());
        user.setRoles(Collections.singletonList(roleRepository.findByRoleName(RoleEnum.USER.getRoleName())));
        user.setLastName(userDto.getLastName());
        return userRepository.save(user);
    }
}
