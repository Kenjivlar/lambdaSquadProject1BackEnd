package com.loanmanagement.service;

import com.loanmanagement.advice.NotFoundException;
import com.loanmanagement.model.AccountsModel;
import com.loanmanagement.repo.AccountsRepository;
import com.loanmanagement.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountsRepository accountsRepository;

    public CustomUserDetailsService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountsModel appUser = accountsRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        return User.builder()
                .username(appUser.getEmail())
                .password(appUser.getPassword())
                .disabled(false)
                // Puedes asignar roles o authorities según corresponda.
                .authorities("ROLE_USER")
                .build();
    }
}
