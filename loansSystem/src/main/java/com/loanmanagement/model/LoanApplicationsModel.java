package com.loanmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan_applications")
public class LoanApplicationsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_application_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "loan_type_id", nullable = false)
    private LoanTypes loanType;

    @ManyToOne
    @JoinColumn(name = "application_status_id", nullable = false)
    private StatusesModel status;

    @Column(name = "interest", nullable = false)
    private BigDecimal interest;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "tittle", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "application_date", nullable = false)
    private LocalDate applicationDate;
}