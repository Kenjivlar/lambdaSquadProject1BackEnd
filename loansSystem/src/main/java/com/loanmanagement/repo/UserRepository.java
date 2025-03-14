package com.loanmanagement.repo;

import com.loanmanagement.model.LoanApplicationsModel;
import com.loanmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
