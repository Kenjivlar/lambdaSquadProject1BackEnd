package com.loanmanagement.controller;

import com.loanmanagement.model.AccountsModel;
import com.loanmanagement.service.AccountsService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(value = "http://localhost:5173")
public class AuthController {

    public final AccountsService accountsService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AccountsService accountsService){this.accountsService = accountsService;}

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AccountsModel account, HttpSession session) {

        Optional<AccountsModel> accountOpt = accountsService.validateUser(account.getEmail(), account.getPassword());
        if (accountOpt.isPresent()) {
            session.setAttribute("account", accountOpt.get());
            logger.debug("Trying to log in with email: {}", accountOpt.get().getEmail());
            logger.info("Successful login with user: {}", accountOpt.get().getEmail());
            return ResponseEntity.ok("Welcome: " + accountOpt.get().getEmail());
        } else {
            logger.warn("Login failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/session-check")
    public ResponseEntity<?> checkSession(HttpSession session) {
        AccountsModel loggedAccount = (AccountsModel) session.getAttribute("account");
        if (loggedAccount != null) {
            return ResponseEntity.ok(loggedAccount); // Return user details if logged in
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session invalid or expired");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        logger.info("Successful logout");
        return ResponseEntity.ok("Logged out successfully");
    }

}