package com.loanmanagement.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class StatusesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_status_id")
    private Integer id;
=======
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "application_statuses")
public class StatusesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_status_id")
    private int id;
>>>>>>> f62977ca6a46985aa936931637058ade46b187e8

    @Column(name = "status",  length = 50)
    private String status;

<<<<<<< HEAD
=======

>>>>>>> f62977ca6a46985aa936931637058ade46b187e8
}