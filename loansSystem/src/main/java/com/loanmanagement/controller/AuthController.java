package com.loanmanagement.controller;

import com.loanmanagement.model.AccountsModel;
import com.loanmanagement.service.AccountsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AuthController {

    public final AccountsService accountsService;

    public AuthController(AccountsService accountsService){this.accountsService = accountsService;}

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AccountsModel account, HttpSession session) {

        Optional<AccountsModel> accountOpt = accountsService.validateUser(account.getEmail(), account.getPassword());
        if (accountOpt.isPresent()) {
            session.setAttribute("account", accountOpt.get());
            System.out.println(accountOpt.get().getEmail());
            return ResponseEntity.ok("Welcome" + accountOpt.get().getEmail());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }

}