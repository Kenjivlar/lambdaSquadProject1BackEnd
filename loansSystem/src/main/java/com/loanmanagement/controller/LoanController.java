package com.loanmanagement.controller;

import com.loanmanagement.dto.LoanApplicationRequest;
import com.loanmanagement.dto.UpdateLoanStatusDTO;
import com.loanmanagement.model.AccountsModel;
import com.loanmanagement.model.LoanApplicationsModel;
import com.loanmanagement.model.LoanTypes;
import com.loanmanagement.model.User;
import com.loanmanagement.service.LoanService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity<List<LoanApplicationsModel>> getAllLoans(){
        return ResponseEntity.ok(loanService.findAllLoans());
    }

    @GetMapping("/{id}") //Find by phone number
    public ResponseEntity<LoanApplicationsModel> getLoanById(@PathVariable Long id){
        return loanService.findLoanById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createLoan(@RequestBody LoanApplicationRequest loanDTO, HttpSession session) {
        AccountsModel sessionAccount = (AccountsModel) session.getAttribute("account");
        if(sessionAccount == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }

        // Obtener el usuario de la sesión
        User currentUser = sessionAccount.getUser();
        if(currentUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User profile not complete");
        }

        // Validar campos obligatorios
        if(loanDTO.getTitle() == null || loanDTO.getDescription() == null) {
            return ResponseEntity.badRequest().body("Title and description are required");
        }

        // Crear el modelo de préstamo
        LoanApplicationsModel newLoan = new LoanApplicationsModel();
        newLoan.setUserId(currentUser);
        newLoan.setTitle(loanDTO.getTitle());
        newLoan.setDescription(loanDTO.getDescription());
        newLoan.setApplicationDate(LocalDate.now());

        // Configurar el tipo de préstamo
        LoanTypes loanType = new LoanTypes();
        loanType.setId(loanDTO.getLoanTypeId());
        newLoan.setLoanType(loanType);

        // Configurar los valores del préstamo
        newLoan.setAmount(loanDTO.getAmount());
        newLoan.setInterest(loanDTO.getInterestRate());

        // Guardar el préstamo
        try {
            LoanApplicationsModel savedLoan = loanService.createLoan(newLoan);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLoan);
        } catch (Exception e) {
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

        // Verificar sesión y rol de administrador
        AccountsModel sessionAccount = (AccountsModel) session.getAttribute("account");
        if (sessionAccount == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }

        if (!"admin".equals(sessionAccount.getAccountType().getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied. Admin role required");
        }

        // Validar que el estado sea válido ("approved" o "rejected")
        if (!"accepted".equalsIgnoreCase(statusDTO.getStatus()) &&
                !"rejected".equalsIgnoreCase(statusDTO.getStatus())) {
            return ResponseEntity.badRequest().body("Invalid status. Only 'approved' or 'rejected' allowed");
        }

        // Actualizar el estado del préstamo
        LoanApplicationsModel updatedLoan = loanService.updateLoanStatus(loanId, statusDTO.getStatus());
        return ResponseEntity.ok(updatedLoan);
    }

}
