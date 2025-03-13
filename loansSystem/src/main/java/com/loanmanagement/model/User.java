package com.loanmanagement.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD:loansSystem/src/main/java/com/loanmanagement/model/User.java
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;
=======
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
>>>>>>> f62977ca6a46985aa936931637058ade46b187e8:loansSystem/src/main/java/com/loanmanagement/model/UserModel.java
    private String firstName;

    @Column(name = "last_name",  length = 50)
    private String lastName;

    @Column(name = "phone_number",  length = 50)
    private int phoneNumber;

    @Column(name = "credit_score")
    private int creditScore;

<<<<<<< HEAD:loansSystem/src/main/java/com/loanmanagement/model/User.java
    public User() {
    }

    public User(String firstName, String lastName, int phoneNumber, int creditScore) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.creditScore = creditScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }
=======


>>>>>>> f62977ca6a46985aa936931637058ade46b187e8:loansSystem/src/main/java/com/loanmanagement/model/UserModel.java
}