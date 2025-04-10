package com.loanmanagement.config;

import com.loanmanagement.model.*;
import com.loanmanagement.repo.*;
import org.mindrot.jbcrypt.BCrypt;
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


//    @Bean
//    public CommandLineRunner loadDataAccountAndUser(AccountsRepository accountsRepository, UserRepository userRepository) {
//        return args -> {
//            if (accountsRepository.count() == 0) {
//                AccountTypeModel accountTypeModel = new AccountTypeModel();
//                accountTypeModel.setId(1L);
//                AccountsModel adminAccount = new AccountsModel();
//                adminAccount.setEmail("admin@mail.com");
//                adminAccount.setPassword(BCrypt.hashpw("admin1234", BCrypt.gensalt(12)));
//                adminAccount.setAccountType(accountTypeModel);
//                adminAccount = accountsRepository.save(adminAccount);
//                //here its saved into the same variable the whole saved object just to get the accountType
//
//
//                User adminUser = new User();
//                adminUser.setFirstName("Admin");
//                adminUser.setLastName("Manager");
//                adminUser.setPhoneNumber("5582901910");
//                adminUser.setCreditScore(50000);
//                adminUser.setAccount(adminAccount);
//                //here I set the Account by the previous saved variable, that contains the whole Account
//                userRepository.save(adminUser);
//
//
//            }
//        };
//    }

    @Bean
    public CommandLineRunner loadLoanData(LoanTypesRepository loanTypeRepository) {
        return args -> {
            if (loanTypeRepository.count() == 0) {
                LoanTypes personalLoan = new LoanTypes();
                personalLoan.setLoanType("Personal");
                loanTypeRepository.save(personalLoan);

                LoanTypes mortgageLoan = new LoanTypes();
                mortgageLoan.setLoanType("Mortgage");
                loanTypeRepository.save(mortgageLoan);

                LoanTypes autoLoan = new LoanTypes();
                autoLoan.setLoanType("Auto");
                loanTypeRepository.save(autoLoan);

                LoanTypes debtConsolidationLoan = new LoanTypes();
                debtConsolidationLoan.setLoanType("Debt Consolidation");
                loanTypeRepository.save(debtConsolidationLoan);

                LoanTypes businessLoan = new LoanTypes();
                businessLoan.setLoanType("Business");
                loanTypeRepository.save(businessLoan);

                LoanTypes educationLoan = new LoanTypes();
                educationLoan.setLoanType("Education");
                loanTypeRepository.save(educationLoan);

                LoanTypes healthLoan = new LoanTypes();
                healthLoan.setLoanType("Health");
                loanTypeRepository.save(healthLoan);

                LoanTypes homeImprovementLoan = new LoanTypes();
                homeImprovementLoan.setLoanType("Home Improvement");
                loanTypeRepository.save(homeImprovementLoan);

                LoanTypes vacationLoan = new LoanTypes();
                vacationLoan.setLoanType("Vacation");
                loanTypeRepository.save(vacationLoan);

                LoanTypes investmentLoan = new LoanTypes();
                investmentLoan.setLoanType("Investment");
                loanTypeRepository.save(investmentLoan);

                LoanTypes emergencyLoan = new LoanTypes();
                emergencyLoan.setLoanType("Emergency");
                loanTypeRepository.save(emergencyLoan);

                LoanTypes heavyVehicleLoan = new LoanTypes();
                heavyVehicleLoan.setLoanType("Heavy Vehicle");
                loanTypeRepository.save(heavyVehicleLoan);

                LoanTypes technologyLoan = new LoanTypes();
                technologyLoan.setLoanType("Technology");
                loanTypeRepository.save(technologyLoan);

                LoanTypes weddingLoan = new LoanTypes();
                weddingLoan.setLoanType("Wedding");
                loanTypeRepository.save(weddingLoan);

            }
        };
    }

    @Bean
    public CommandLineRunner loadStatusData(StatusesRepository statusRepository) {
        return args -> {
            if (statusRepository.count() == 0) {
                StatusesModel statusA = new StatusesModel();
                statusA.setStatus("accepted");
                statusRepository.save(statusA);

                StatusesModel statusR = new StatusesModel();
                statusR.setStatus("rejected");
                statusRepository.save(statusR);

                StatusesModel statusP = new StatusesModel();
                statusP.setStatus("pending");
                statusRepository.save(statusP);



            }
        };
    }
}