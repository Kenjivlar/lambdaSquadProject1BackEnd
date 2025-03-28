package com.loanmanagement.controller;

import com.loanmanagement.dto.RegisterUserRequest;
import com.loanmanagement.model.User;
import com.loanmanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserRequest request) {
        User newUser = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        //return ResponseEntity.status(HttpStatus.CONFLICT).body(newUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /*@GetMapping("/by-email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }*/

    @GetMapping("/by-phone")
    public ResponseEntity<User> getUserByPhoneNumber(@RequestParam int phoneNumber) {
        User user = userService.getUserByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        String message = "Hello, World!";
        return ResponseEntity.ok(message);
    }

    @GetMapping("/error")
    public ResponseEntity<String> error() {
        String errorMessage = "Internal Server Error";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);
    }



//    @GetMapping("/by-email")
//    public ResponseEntity<User> findUserByEmail(@RequestParam String email) {
//        User user = userService.getUserByEmail(email);
//        if (user != null) {
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }
}