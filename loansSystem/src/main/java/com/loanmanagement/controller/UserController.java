package com.loanmanagement.controller;

import com.loanmanagement.dto.RegisterUserRequest;
import com.loanmanagement.dto.UpdateUserDTO;
import com.loanmanagement.model.AccountsModel;
import com.loanmanagement.model.LoanApplicationsModel;
import com.loanmanagement.model.User;
import com.loanmanagement.service.LoanService;
import com.loanmanagement.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final LoanService loanService;

    public UserController(UserService userService, LoanService loanService) {
        this.userService = userService;
        this.loanService = loanService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request) {
        try {
            User newUser = userService.registerUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/edit-my-info")
    public ResponseEntity<?> updateCurrentUser(
            @RequestBody UpdateUserDTO updateUserDTO,
            HttpSession session) {

        // Validate session
        AccountsModel sessionAccount = (AccountsModel) session.getAttribute("account");
        if (sessionAccount == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }

        // Get current user
        User currentUser = sessionAccount.getUser();
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User profile not found");
        }

        // Update user
        User updatedUser = userService.updateUser(currentUser.getId(), updateUserDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/my-loans")
    public ResponseEntity<?> getUserLoans(HttpSession session) {
        // Validate session
        AccountsModel sessionAccount = (AccountsModel) session.getAttribute("account");
        if (sessionAccount == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }

        // Get current user
        User currentUser = sessionAccount.getUser();
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User profile not found");
        }

        // Get user´s loans
        List<LoanApplicationsModel> userLoans = loanService.findLoansByUserId(currentUser.getId());
        return ResponseEntity.ok(userLoans);
    }

    @GetMapping("/by-phone")
    public ResponseEntity<User> getUserByPhoneNumber(@RequestParam int phoneNumber) {
        User user = userService.getUserByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(user);
    }
}