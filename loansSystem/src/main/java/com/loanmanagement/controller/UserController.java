package com.loanmanagement.controller;

import com.loanmanagement.model.AccountsModel;
import com.loanmanagement.model.User;
import com.loanmanagement.service.AccountsService;
import com.loanmanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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