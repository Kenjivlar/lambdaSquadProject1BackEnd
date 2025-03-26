package com.loanmanagement.service;

import com.loanmanagement.model.AccountsModel;
import com.loanmanagement.model.AccountTypeModel;
import com.loanmanagement.repo.AccountsRepository;
import com.loanmanagement.repo.AccountTypeRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountsService {

    private final AccountsRepository accountsRepository;
    private final AccountTypeRepository accountTypeRepository;

    public AccountsService(AccountsRepository accountsRepository, AccountTypeRepository accountTypeRepository) {
        this.accountsRepository = accountsRepository;
        this.accountTypeRepository = accountTypeRepository;
    }

    // Save new account
    public AccountsModel saveAccount(AccountsModel account) {
        // Validate that email is available
        if (accountsRepository.findByEmail(account.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exist");
        }

        // Find rol by Id
        AccountTypeModel accountType = accountTypeRepository.findById(account.getAccountType().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid rol"));

        // Assign rol
        account.setAccountType(accountType);

        // Save account in DB
        return accountsRepository.save(account);
    }

    public Optional<AccountsModel> validateUser(String email, String password) {
        Optional<AccountsModel> accountOpt = accountsRepository.findByEmail(email);

        if (accountOpt.isEmpty()) {
            return Optional.empty(); // User not found
        }

        AccountsModel storedAccount = accountOpt.get();

        // Compare the raw password with the hashed password in the database
        if (BCrypt.checkpw(password, storedAccount.getPassword())) {
            return Optional.of(storedAccount); // Password matches, return user
        } else {
            return Optional.empty(); // Password incorrect
        }
    }
}