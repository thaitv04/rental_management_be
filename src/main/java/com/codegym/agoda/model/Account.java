package com.codegym.agoda.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String username;
    private String password;
    private String role;
    private String phoneNumber;


}
