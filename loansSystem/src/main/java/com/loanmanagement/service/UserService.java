package com.loanmanagement.service;

import com.loanmanagement.model.AccountsModel;
import com.loanmanagement.model.User;
import com.loanmanagement.repo.AccountsRepository;
import com.loanmanagement.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountsRepository accountsRepository;

    public UserService(UserRepository userRepository, AccountsRepository accountsRepository) {
        this.userRepository = userRepository;
        this.accountsRepository = accountsRepository;
    }

    public User saveUser(User user) {
        if (accountsRepository.findByEmail(user.getAccount().getEmail()) != null) {
            throw new IllegalArgumentException("Email already exist");
        }

        // First save account
        AccountsModel account = user.getAccount();
        AccountsModel savedAccount = accountsRepository.save(account);

        // Assign count to user
        user.setAccount(savedAccount);

        // Save user in DB
        return userRepository.save(user);
    }
}