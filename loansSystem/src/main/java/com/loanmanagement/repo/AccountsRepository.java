package com.loanmanagement.repo;

import com.loanmanagement.model.AccountTypeModel;
import com.loanmanagement.model.AccountsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<AccountsModel, Long> {
    Optional<AccountsModel> findByEmail(String email);

}
