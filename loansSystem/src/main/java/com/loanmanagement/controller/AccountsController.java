package com.loanmanagement.controller;

import com.loanmanagement.model.AccountsModel;
import com.loanmanagement.service.AccountsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {

    private final AccountsService accountsService;

    public AccountsController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @PostMapping("/register")
    public ResponseEntity<AccountsModel> registerAccount(@RequestBody AccountsModel account) {
        AccountsModel newAccount = accountsService.saveAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
    }
}