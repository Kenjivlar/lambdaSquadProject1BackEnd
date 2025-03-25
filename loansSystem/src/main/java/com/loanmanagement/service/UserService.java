package com.loanmanagement.service;

import com.loanmanagement.dto.RegisterUserRequest;
import com.loanmanagement.model.AccountsModel;
import com.loanmanagement.model.AccountTypeModel;
import com.loanmanagement.model.User;
import com.loanmanagement.repo.AccountsRepository;
import com.loanmanagement.repo.AccountTypeRepository;
import com.loanmanagement.repo.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

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

        String hashedPassword = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt(12));
        // Step 1: Check if a user with the same phone number already exists
        User existingUser = userRepository.findByPhoneNumber(request.getPhoneNumber());
        if (existingUser != null) {
            throw new IllegalArgumentException("User already exists with phone number: " + request.getPhoneNumber());
        }

        // Step 2: Create the account
        AccountsModel account = new AccountsModel();
        account.setEmail(request.getEmail());
        account.setPassword(hashedPassword);

        // Find the account type (role) by ID
        AccountTypeModel accountType = accountTypeRepository.findById(request.getAccountTypeId());
        if (accountType == null) {
            throw new IllegalArgumentException("Invalid account type ID");
        }
        account.setAccountType(accountType);

        // Save the account
        AccountsModel savedAccount = accountsRepository.save(account);

        // Step 3: Create the user and associate it with the account
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCreditScore(request.getCreditScore());
        user.setAccount(savedAccount);

        // Save the user
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /*public User getUserByEmail(String email) {
        AccountsModel account = accountsRepository.findByEmail(email);
        if (account == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
        return userRepository.findByEmail(account);
    }*/

    // UserService.java
    public User getUserByPhoneNumber(int phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user == null) {
            throw new IllegalArgumentException("User not found with phone number: " + phoneNumber);
        }
        return user;
    }
}