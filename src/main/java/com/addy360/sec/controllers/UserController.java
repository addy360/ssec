package com.addy360.sec.controllers;

import com.addy360.sec.dto.UserDto;
import com.addy360.sec.models.User;
import com.addy360.sec.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> index(){
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }


    @PostMapping
    public  ResponseEntity<?> store(@RequestBody @Valid UserDto userDto){
        User user = userService.createUser(userDto);
        return  ResponseEntity.ok(user);
    }
}
