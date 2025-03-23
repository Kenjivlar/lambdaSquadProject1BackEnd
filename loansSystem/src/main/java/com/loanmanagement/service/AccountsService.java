package com.loanmanagement.service;

import com.loanmanagement.model.AccountsModel;
import com.loanmanagement.model.AccountTypeModel;
import com.loanmanagement.repo.AccountsRepository;
import com.loanmanagement.repo.AccountTypeRepository;
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

    // Guardar una nueva cuenta
    public AccountsModel saveAccount(AccountsModel account) {
        // Validar que el email no esté en uso
        if (accountsRepository.findByEmail(account.getEmail()) != null) {
            throw new IllegalArgumentException("El email ya está en uso");
        }

        // Buscar el rol por ID
        AccountTypeModel accountType = accountTypeRepository.findById(account.getAccountType().getId())
                .orElseThrow(() -> new IllegalArgumentException("Rol no válido"));

        // Asignar el rol a la cuenta
        account.setAccountType(accountType);

        // Guardar la cuenta en la base de datos
        return accountsRepository.save(account);
    }

    public Optional<AccountsModel> validateUser(String email, String password) {
        // For simplicity, we assume passwords are stored in plain text.
        // In production, you'd use a PasswordEncoder/BCrypt to compare hashed passwords.
        return accountsRepository.findByEmailAndPassword(email, password);
    }
}