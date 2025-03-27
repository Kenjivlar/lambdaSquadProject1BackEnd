package com.loanmanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.loanmanagement.dto.RegisterUserRequest;
import com.loanmanagement.dto.UpdateUserDTO;
import com.loanmanagement.model.AccountTypeModel;
import com.loanmanagement.model.AccountsModel;
import com.loanmanagement.model.User;
import com.loanmanagement.repo.AccountsRepository;
import com.loanmanagement.repo.AccountTypeRepository;
import com.loanmanagement.repo.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountsRepository accountsRepository;

    @Mock
    private AccountTypeRepository accountTypeRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterAndUpdateUser() {
        // Create test user
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.setFirstName("Ángel");
        registerRequest.setLastName("González");
        registerRequest.setPhoneNumber("9876543210");
        registerRequest.setEmail("angel@example.com");
        registerRequest.setPassword("password123");
        registerRequest.setCreditScore(700);
        registerRequest.setAccountTypeId(1L);

        // Simulate AccountType and Account in the DB
        AccountTypeModel accountType = new AccountTypeModel();
        accountType.setId(1L);
        when(accountTypeRepository.findById(1L)).thenReturn(accountType);

        AccountsModel account = new AccountsModel();
        account.setId(1L);
        account.setEmail("angel@example.com");
        when(accountsRepository.save(any(AccountsModel.class))).thenReturn(account);

        // Simulate user saved in the DB
        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setFirstName(registerRequest.getFirstName());
        savedUser.setLastName(registerRequest.getLastName());
        savedUser.setPhoneNumber(registerRequest.getPhoneNumber());
        savedUser.setAccount(account);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(userRepository.findById(1L)).thenReturn(Optional.of(savedUser));

        // Step 1: Register user
        User registeredUser = userService.registerUser(registerRequest);
        assertNotNull(registeredUser);
        assertEquals("Ángel", registeredUser.getFirstName());

        // Step 2: Update user
        UpdateUserDTO updateRequest = new UpdateUserDTO();
        updateRequest.setLastName("Ramírez");

        when(userRepository.save(any(User.class))).thenReturn(savedUser); // Simulate update

        User updatedUser = userService.updateUser(1L, updateRequest);
        assertNotNull(updatedUser);
        assertEquals("Ramírez", updatedUser.getLastName());
    }

    @Test
    void testRegisterUserWithExistingPhoneNumber() {
        // Simulate that the user already exists
        when(userRepository.findByPhoneNumber("9876543210")).thenReturn(new User());

        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.setPhoneNumber("9876543210");

        // Must throw an exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(registerRequest);
        });

        assertEquals("User already exists with phone number: 9876543210", exception.getMessage());
    }

    @Test
    void testUpdateNonExistentUser() {
        // Pretend that the user does not exist
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        UpdateUserDTO updateRequest = new UpdateUserDTO();
        updateRequest.setFirstName("Nuevo Nombre");

        // Must throw an exception
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.updateUser(99L, updateRequest);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testGetUserByPhoneNumber() {
        // Simulate user in DB
        User user = new User();
        user.setId(1L);
        user.setFirstName("Carlos");
        user.setPhoneNumber("1234567890");

        when(userRepository.findByPhoneNumber("1234567890")).thenReturn(user);

        // Search for user by phone
        User foundUser = userService.getUserByPhoneNumber(1234567890);

        assertNotNull(foundUser);
        assertEquals("Carlos", foundUser.getFirstName());
        assertEquals("1234567890", foundUser.getPhoneNumber());
    }
}
