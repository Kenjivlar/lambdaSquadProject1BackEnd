package com.loanmanagement.repo;

import com.loanmanagement.model.StatusesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusesRepository extends JpaRepository<StatusesModel, Long> {
}
