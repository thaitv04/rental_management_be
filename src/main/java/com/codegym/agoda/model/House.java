package com.codegym.agoda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "house")
@Data
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String description;
    private double price;
    private int numberOfBedRoom;
    private int numberOfBathRoom;
    //doanh thu
    private double revenue;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Room> rooms;

    @ManyToOne
    private Status status;

    @ManyToOne
    private Account account;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Image> images;

}
