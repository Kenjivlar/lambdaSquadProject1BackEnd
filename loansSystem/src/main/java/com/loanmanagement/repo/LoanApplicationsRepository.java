package com.loanmanagement.repo;

import com.loanmanagement.model.LoanApplicationsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanApplicationsRepository extends JpaRepository<LoanApplicationsModel, Long> {
}