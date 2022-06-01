package com.addy360.sec.controllers;

import com.addy360.sec.dto.UserDto;
import com.addy360.sec.models.User;
import com.addy360.sec.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<?> index(){
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }


    @PostMapping
    public  ResponseEntity<?> store(@RequestBody @Valid UserDto userDto){
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userService.createUser(userDto);
        return  ResponseEntity.ok(user);
    }
}
