package com.loanmanagement.repo;

import com.loanmanagement.model.AccountsModel;
import com.loanmanagement.model.LoanApplicationsModel;
import com.loanmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Optional<User> findByPhoneNumber(int phoneNumber);
    User findByPhoneNumber(int phoneNumber);
}
