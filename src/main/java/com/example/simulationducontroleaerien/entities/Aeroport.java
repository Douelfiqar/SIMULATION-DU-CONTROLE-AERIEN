package com.example.simulationducontroleaerien.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Aeroport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAeroport;
    @Column(name = "name", unique = true)
    private String name;
    private String localisation;
    private int nombrePistes;
    private int nombreDePlaceAuSol;
    private int delaiAttenteAuSol;
    private int tempsAccessAuxPistes;
    private int delaiAntiCollision;
    private int tempsDecollageAtterrissage;
    private int dureeBoucleAttente;
    @ElementCollection
    private Map<Aeroport, Double> distanceAuxAutresAeroports;

    @ManyToOne
    private Escale escale;
    @OneToMany(mappedBy = "aeroportDepart")
    private Collection<Vol> volDepart;
    @OneToMany(mappedBy = "aeroportArrivee")
    private Collection<Vol> volArrivee;

}
