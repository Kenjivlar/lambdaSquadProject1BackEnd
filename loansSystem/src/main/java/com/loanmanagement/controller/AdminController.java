package com.loanmanagement.controller;

import com.loanmanagement.model.AccountsModel;
import com.loanmanagement.model.LoanApplicationsModel;
import com.loanmanagement.service.LoanService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final LoanService loanService;

    public AdminController(LoanService loanService) {
        this.loanService = loanService;
    }


    @GetMapping("/loans")
    public ResponseEntity<?> getAllLoansAdmin(HttpSession session) {
        // Verificar sesión
        AccountsModel sessionAccount = (AccountsModel) session.getAttribute("account");
        if (sessionAccount == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }

        // Verificar rol de administrador
        if (!"admin".equals(sessionAccount.getAccountType().getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied. Admin role required");
        }

        // Obtener todos los préstamos
        List<LoanApplicationsModel> loans = loanService.findAllLoans();
        return ResponseEntity.ok(loans);
    }
}
