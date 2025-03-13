//package com.loanmanagement.model;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//
//@Entity
//@Table(name = "user_profiles")
//
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_profile_id")
//    private Long id;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    private AccountsModel account_id;
//
//    @Column(name = "first_name", length = 50)
//    private String name;
//
//    @Column(name = "last_name", length = 50)
//    private String lastname;
//
//    @Column(name = "phone_number", length = 20)
//    private String phone;
//
//    @Column(name = "credit_score")
//    private Long credit;
//
//}

package com.loanmanagement.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private int creditScore;




    public User() {    }
    public User(String firstName, String lastName, int phoneNumber, int creditScore) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.creditScore = creditScore;    }

    public Long getId() {        return id;    }
    public void setId(Long id) {        this.id = id;    }

    public String getFirstName() {        return firstName;    }
    public void setFirstName(String firstName) {        this.firstName = firstName;    }

    public String getLastName() {        return lastName;    }
    public void setLastName(String lastName) {        this.lastName = lastName;    }

    public int getPhoneNumber() {        return phoneNumber;    }
    public void setPhoneNumber(int phoneNumber) {        this.phoneNumber = phoneNumber;    }

    public int getCreditScore() {        return creditScore;    }
    public void setCreditScore(int creditScore) {        this.creditScore = creditScore;    }
}