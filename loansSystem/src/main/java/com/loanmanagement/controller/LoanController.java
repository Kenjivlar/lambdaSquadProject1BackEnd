package com.loanmanagement.controller;

import com.loanmanagement.dto.LoanApplicationRequest;
import com.loanmanagement.dto.UpdateLoanStatusDTO;
import com.loanmanagement.model.AccountsModel;
import com.loanmanagement.model.LoanApplicationsModel;
import com.loanmanagement.model.LoanTypes;
import com.loanmanagement.model.User;
import com.loanmanagement.service.LoanService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;
    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity<List<LoanApplicationsModel>> getAllLoans(){
        return ResponseEntity.ok(loanService.findAllLoans());
    }

    @GetMapping("/{id}") //Find user by phone number
    public ResponseEntity<LoanApplicationsModel> getLoanById(@PathVariable Long id){
        return loanService.findLoanById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createLoan(@RequestBody LoanApplicationRequest loanDTO, HttpSession session) {
        AccountsModel sessionAccount = (AccountsModel) session.getAttribute("account");
        if(sessionAccount == null) {
            logger.warn("Attempt to create loan without authentication");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }

        // Get user session
        User currentUser = sessionAccount.getUser();
        if(currentUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User profile not complete");
        }

        // Validate required fields
        if(loanDTO.getTitle() == null || loanDTO.getDescription() == null) {
            return ResponseEntity.badRequest().body("Title and description are required");
        }

        // Create loan model
        LoanApplicationsModel newLoan = new LoanApplicationsModel();
        newLoan.setUserId(currentUser);
        newLoan.setTitle(loanDTO.getTitle());
        newLoan.setDescription(loanDTO.getDescription());
        newLoan.setApplicationDate(LocalDate.now());

        // Set loan type
        LoanTypes loanType = new LoanTypes();
        loanType.setId(loanDTO.getLoanTypeId());
        newLoan.setLoanType(loanType);

        // Configure loan values
        newLoan.setAmount(loanDTO.getAmount());
        newLoan.setInterest(loanDTO.getInterestRate());

        // Save loans
        try {
            LoanApplicationsModel savedLoan = loanService.createLoan(newLoan);
            logger.info("Creating a new loan for a user: {}", sessionAccount.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLoan);
        } catch (Exception e) {
            logger.warn("Error creating a new loan for a user: {}", sessionAccount.getEmail());
            return ResponseEntity.internalServerError().body("Error creating loan: " + e.getMessage());
        }
    }

    /**
     * Updates an existing loan.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLoan(@PathVariable Long id, @RequestBody LoanApplicationsModel loanDetails, HttpSession session){
        AccountsModel sessionAccount = (AccountsModel) session.getAttribute("account");
        if(sessionAccount == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }

        if(!"admin".equals(sessionAccount.getAccountType().getRole()) && !sessionAccount.getId().equals(id)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acces denied");
        }

        return loanService.updateLoan(id, loanDetails).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
    * Deletes a loan.
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable Long id, HttpSession session){
        AccountsModel sessionAccount = (AccountsModel) session.getAttribute("account");
        if(sessionAccount == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }

        if(!"admin".equals(sessionAccount.getAccountType().getRole())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acces denied");
        }

        boolean deleted = loanService.deleteLoan(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/admin/{loanId}/status")
    public ResponseEntity<?> updateLoanStatus(
            @PathVariable Long loanId,
            @RequestBody UpdateLoanStatusDTO statusDTO,
            HttpSession session) {

        // Validate session and rol
        AccountsModel sessionAccount = (AccountsModel) session.getAttribute("account");
        if (sessionAccount == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }

        if (!"admin".equals(sessionAccount.getAccountType().getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied. Admin role required");
        }

        // Validate loan status ("approved" or "rejected")
        if (!"accepted".equalsIgnoreCase(statusDTO.getStatus()) &&
                !"rejected".equalsIgnoreCase(statusDTO.getStatus())) {
            return ResponseEntity.badRequest().body("Invalid status. Only 'approved' or 'rejected' allowed");
        }

        // Update loan status
        LoanApplicationsModel updatedLoan = loanService.updateLoanStatus(loanId, statusDTO.getStatus());
        return ResponseEntity.ok(updatedLoan);
    }

}
