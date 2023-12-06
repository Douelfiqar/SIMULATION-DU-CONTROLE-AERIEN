package com.example.simulationducontroleaerien.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany
    private Collection<Avion> avionCollection;
}
