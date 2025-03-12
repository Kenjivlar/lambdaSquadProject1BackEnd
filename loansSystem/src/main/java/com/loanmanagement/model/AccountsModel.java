package com.loanmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "accounts")
public class AccountsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer id;

    @Column(unique = true,length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column()
    private Integer idAccountType;
    
}