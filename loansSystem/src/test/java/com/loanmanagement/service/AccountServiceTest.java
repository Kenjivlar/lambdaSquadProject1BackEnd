package com.loanmanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.loanmanagement.model.AccountsModel;
import com.loanmanagement.model.AccountTypeModel;
import com.loanmanagement.repo.AccountsRepository;
import com.loanmanagement.repo.AccountTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

class AccountsServiceTest {

    @Mock
    private AccountsRepository accountsRepository;

    @Mock
    private AccountTypeRepository accountTypeRepository;

    @InjectMocks
    private AccountsService accountsService;

    private AccountsModel account;
    private AccountTypeModel accountType;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Common data
        accountType = new AccountTypeModel();
        accountType.setId(1L);
        accountType.setRole("Standard");

        account = new AccountsModel();
        account.setEmail("test@example.com");
        account.setPassword(BCrypt.hashpw("password123", BCrypt.gensalt()));
        account.setAccountType(accountType);
    }

    @Test
    void testSaveAccountWithExistingEmail() {
        // Simulates that a user with the same email already exists
        when(accountsRepository.findByEmail("test@example.com")).thenReturn(Optional.of(account));

        // Try saving the account with an already registered email
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            accountsService.saveAccount(account);
        });

        assertEquals("Email already exist", exception.getMessage());
    }

    @Test
    void testSaveAccountWithInvalidRole() {
        // Pretend the role doesn't exist
        when(accountTypeRepository.findById(account.getAccountType().getId()))
                .thenReturn(Optional.empty());

        // Trying to save the account with an invalid role
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            accountsService.saveAccount(account);
        });

        assertEquals("Invalid rol", exception.getMessage());
    }

    @Test
    void testValidateUserWithCorrectPassword() {
        // Pretends that the account exists
        when(accountsRepository.findByEmail("test@example.com")).thenReturn(Optional.of(account));

        // Validate user with the correct password
        Optional<AccountsModel> validatedAccount = accountsService.validateUser("test@example.com", "password123");

        assertTrue(validatedAccount.isPresent());
        assertEquals("test@example.com", validatedAccount.get().getEmail());
    }

    @Test
    void testValidateUserWithIncorrectPassword() {
        // Simulates that the account exists but with an incorrect password
        when(accountsRepository.findByEmail("test@example.com")).thenReturn(Optional.of(account));

        // Attempt to validate user with incorrect password
        Optional<AccountsModel> validatedAccount = accountsService.validateUser("test@example.com", "wrongPassword");

        assertFalse(validatedAccount.isPresent());
    }
}
