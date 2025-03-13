package com.loanmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanTypesRepository extends JpaRepository<com.loanmanagement.model.LoanTypes, Long> {
}
