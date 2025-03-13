package com.loanmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan_applications")
public class LoanApplicationsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column()
    private Integer idUser;
    @Column()
    private Integer idLoanType;
    @Column(nullable = false)
    private Integer statusID;
    @Column(nullable = false,name = "application_status_id")
    private Double amountRequested;

}