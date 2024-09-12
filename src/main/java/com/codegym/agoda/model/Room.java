package com.codegym.agoda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "room")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


    @ManyToOne
    private TypeRoom typeRoom;

    @ManyToOne
    @JoinColumn(name = "id_house")
    @JsonIgnore
    private House house;
}
