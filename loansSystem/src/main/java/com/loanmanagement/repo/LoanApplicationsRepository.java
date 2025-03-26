package com.loanmanagement.repo;

import com.loanmanagement.model.LoanApplicationsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationsRepository extends JpaRepository<LoanApplicationsModel, Long> {
    List<LoanApplicationsModel> findByUserId_Id(Long userId);
}