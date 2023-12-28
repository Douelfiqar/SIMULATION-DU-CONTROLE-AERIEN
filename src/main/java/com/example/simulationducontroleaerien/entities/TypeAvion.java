package com.example.simulationducontroleaerien.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class TypeAvion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", unique = true)
    private String name;
    private double vitesseNormale;
    private double vitesseBoucleAttente;
    private double consomationNormale;
    private double consomationBoucleAttente;

    @OneToMany(mappedBy = "typeAvion")
    private Collection<Avion> avionCollection = new ArrayList<>();
}
