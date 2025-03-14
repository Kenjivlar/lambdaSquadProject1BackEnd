package com.loanmanagement.repo;

import com.loanmanagement.model.AccountsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<AccountsModel, Long> {
    AccountsModel findByEmail(String email);
}