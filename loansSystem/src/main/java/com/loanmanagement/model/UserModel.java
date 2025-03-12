package com.loanmanagement.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_profiles")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private int id;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private AccountsModel idAccounts;

    @Column(name = "first_name",  length = 50)
    private String firstName;

    @Column(name = "last_name",  length = 50)
    private String lastName;

    @Column(name = "phone_number",  length = 50)
    private int phoneNumber;

    @Column(name = "credit_score")
    private int creditScore;



}