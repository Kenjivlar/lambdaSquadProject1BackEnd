package com.loanmanagement.repo;

import com.loanmanagement.model.StatusesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusesRepository extends JpaRepository<StatusesModel, Long> {
    Optional<StatusesModel> findByStatus(String status);

    Optional<StatusesModel> findByStatusIgnoreCase(String status);
}