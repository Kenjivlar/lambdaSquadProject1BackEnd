package com.loanmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan_types")
public class LoanTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_type_id")
    private Integer id;
    @Column(name = "name",  length = 50, nullable = false)
    private String loanType;

=======
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loan_types")
public class LoanTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_type_id")
    private int id;


    @Column(name = "name",  length = 50)
    private String loanType;


>>>>>>> f62977ca6a46985aa936931637058ade46b187e8
}