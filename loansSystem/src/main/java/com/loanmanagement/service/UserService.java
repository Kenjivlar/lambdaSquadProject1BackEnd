package com.loanmanagement.service;

import com.loanmanagement.dto.RegisterUserRequest;
import com.loanmanagement.model.AccountTypeModel;
import com.loanmanagement.model.AccountsModel;
import com.loanmanagement.model.User;
import com.loanmanagement.repo.AccountTypeRepository;
import com.loanmanagement.repo.AccountsRepository;
import com.loanmanagement.repo.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountsRepository accountsRepository;
    private final AccountTypeRepository accountTypeRepository;

    public UserService(UserRepository userRepository, AccountsRepository accountsRepository, AccountTypeRepository accountTypeRepository) {
        this.userRepository = userRepository;
        this.accountsRepository = accountsRepository;
        this.accountTypeRepository = accountTypeRepository;
    }

    @Transactional
    public User registerUser(RegisterUserRequest request) {
        // Step 1: Create the account
        AccountsModel account = new AccountsModel();
        account.setEmail(request.getEmail());
        account.setPassword(request.getPassword());

        // Find the account type (role) by ID
        AccountTypeModel accountType = accountTypeRepository.findById(request.getAccountTypeId());
        if (accountType == null) {
            throw new IllegalArgumentException("Invalid account type ID");
        }
        account.setAccountType(accountType);

        // Save the account
        AccountsModel savedAccount = accountsRepository.save(account);

        // Step 2: Create the user and associate it with the account
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCreditScore(request.getCreditScore());
        user.setAccount(savedAccount);

        // Save the user
        return userRepository.save(user);
    }

    /*public User saveUser(User user) {
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
    }*/
}