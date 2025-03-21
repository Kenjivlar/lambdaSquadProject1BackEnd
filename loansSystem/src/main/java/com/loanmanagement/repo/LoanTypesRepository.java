package com.loanmanagement.repo;

import com.loanmanagement.model.AccountTypeModel;
import com.loanmanagement.model.LoanTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanTypesRepository extends JpaRepository<com.loanmanagement.model.LoanTypes, Long> {
    LoanTypes findById(long id);
}