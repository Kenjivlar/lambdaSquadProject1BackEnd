package com.loanmanagement.service;

import com.loanmanagement.model.*;
import com.loanmanagement.repo.LoanApplicationsRepository;
import com.loanmanagement.repo.LoanTypesRepository;
import com.loanmanagement.repo.StatusesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanApplicationsRepository loanRepository;
    private final StatusesRepository statusRepository;
    private final LoanTypesRepository loanTypesRepository;

    public LoanService(LoanApplicationsRepository loanRepository,
                       StatusesRepository statusRepository,
                       LoanTypesRepository loanTypesRepository) {
        this.loanRepository = loanRepository;
        this.statusRepository = statusRepository;
        this.loanTypesRepository = loanTypesRepository;
    }

    public List<LoanApplicationsModel> findAllLoans(){
        return loanRepository.findAll();
    }

    public Optional<LoanApplicationsModel> findLoanById(Long id){
        return loanRepository.findById(id);
    }

    public List<LoanApplicationsModel> findLoansByUserId(Long userId) {
        return loanRepository.findByUserId_Id(userId);
    }

    @Transactional
    public LoanApplicationsModel createLoan(LoanApplicationsModel newLoan) {
        // Extra validations
        if(newLoan.getTitle() == null || newLoan.getTitle().isBlank()) {
            throw new IllegalArgumentException("Loan title is required");
        }

        if(newLoan.getDescription() == null || newLoan.getDescription().isBlank()) {
            throw new IllegalArgumentException("Loan description is required");
        }

        // Make pending status by default
        StatusesModel pendingStatus = statusRepository.findByStatus("pending")
                .orElseThrow(() -> new RuntimeException("Pending status not found"));
        newLoan.setStatus(pendingStatus);

        // Set loan type
        if(newLoan.getLoanType() == null || newLoan.getLoanType().getId() == null) {
            throw new IllegalArgumentException("Loan type is required");
        }

        LoanTypes loanType = loanTypesRepository.findById(newLoan.getLoanType().getId())
                .orElseThrow(() -> new RuntimeException("Loan type not found"));
        newLoan.setLoanType(loanType);

        // Set date
        if(newLoan.getApplicationDate() == null) {
            newLoan.setApplicationDate(LocalDate.now());
        }

        return loanRepository.save(newLoan);
    }

    public Optional<LoanApplicationsModel> updateLoan(Long id, LoanApplicationsModel loanDetails){
        return loanRepository.findById(id).map(existingLoan -> {
            existingLoan.setAmount(loanDetails.getAmount());
            existingLoan.setStatus(loanDetails.getStatus());
            return loanRepository.save(existingLoan);
        });
    }

    @Transactional
    public LoanApplicationsModel updateLoanStatus(Long loanId, String status) {
        LoanApplicationsModel loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        StatusesModel newStatus = statusRepository.findByStatusIgnoreCase(status)
                .orElseThrow(() -> new RuntimeException("Status not found"));

        loan.setStatus(newStatus);
        return loanRepository.save(loan);
    }

    public boolean deleteLoan(Long id){
        return loanRepository.findById(id).map(loanApplicationsModel -> {
            loanRepository.delete(loanApplicationsModel);
            return true;
        }).orElse(false);
    }

}
