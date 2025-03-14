package com.loanmanagement.config;

import com.loanmanagement.model.AccountTypeModel;
import com.loanmanagement.repo.AccountTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(AccountTypeRepository accountTypeRepository) {
        return args -> {
            if (accountTypeRepository.count() == 0) {
                AccountTypeModel adminRole = new AccountTypeModel();
                adminRole.setRole("admin");
                accountTypeRepository.save(adminRole);

                AccountTypeModel regularUserRole = new AccountTypeModel();
                regularUserRole.setRole("regular user");
                accountTypeRepository.save(regularUserRole);

            }
        };
    }
}