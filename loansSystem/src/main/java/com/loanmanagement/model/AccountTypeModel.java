package com.loanmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
<<<<<<< HEAD
@Entity
@Table(name = "account_types")
=======
@Entity(name = "account_types")
>>>>>>> f62977ca6a46985aa936931637058ade46b187e8
public class AccountTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_type_id")
    private Integer id;
    @Column(length = 50,name = "type_name",nullable = false)
    private String typeName;
<<<<<<< HEAD

    public AccountTypeModel(String typeName) {

        this.typeName = typeName;

    }

=======
>>>>>>> f62977ca6a46985aa936931637058ade46b187e8
}