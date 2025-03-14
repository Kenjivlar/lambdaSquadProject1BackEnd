package com.loanmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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

    @OneToOne(cascade = CascadeType.ALL)
    private User userid;

    @ManyToOne
    @JoinColumn(name = "loan_type_id", nullable = false)
    private LoanTypes loan;

    @OneToOne(cascade = CascadeType.ALL)
    private StatusesModel status;

    @Column(name = "amount_requested")
    private BigDecimal amount;




}