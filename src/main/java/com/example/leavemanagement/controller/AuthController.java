package com.example.leavemanagement.controller;


import org.springframework.web.bind.annotation.*;

import com.example.leavemanagement.entity.User;
import com.example.leavemanagement.repository.UserRepository;

@RestController
public class AuthController {

   private final UserRepository userRepository;

// Constructor Injection
public AuthController(UserRepository userRepository) {
    this.userRepository = userRepository;
}
    @PostMapping("/register")
public String registerUser(@RequestBody User user) {

    User existingUser =
            userRepository.findByEmail(user.getEmail());

    if(existingUser != null) {

        return "Email Already Registered";
    }

    user.setRole("EMPLOYEE");

    userRepository.save(user);

    return "User Registered Successfully";
}

    @PostMapping("/login")
public String loginUser(@RequestBody User user) {

    User existingUser =
            userRepository.findByEmail(user.getEmail());

    if(existingUser == null) {
        return "User Not Found";
    }

    if(existingUser.getPassword()
        .equals(user.getPassword())) {

    return existingUser.getRole();
}

    return "Invalid Password";
}
}