package com.loanmanagement.repo;

import com.loanmanagement.model.AccountTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountTypeModel, Long> {
    // Find account type by ID (returns null if not found)
    AccountTypeModel findById(long id);
}