package com.codegym.agoda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "image")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameImage;

    @ManyToOne
    @JoinColumn(name = "id_house")
    @JsonIgnore
    private House house;
}
