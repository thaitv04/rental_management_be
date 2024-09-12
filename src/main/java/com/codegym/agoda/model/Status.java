package com.codegym.agoda.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "status")
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

}
