package com.example.simulationducontroleaerien.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Aeroport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAeroport;
    // we don't allow the user to change name
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
    private Double x;
    private Double y;
    @ElementCollection
    private Map<Aeroport, Double> distanceAuxAutresAeroports;

    @OneToMany(mappedBy = "aeroport")
    @JsonBackReference
    private Collection<Escale> escale = new ArrayList<>();
    @OneToMany(mappedBy = "aeroportDepart")
    @JsonBackReference
    private Collection<Vol> volDepart;
    @OneToMany(mappedBy = "aeroportArrivee")
    @JsonBackReference
    private Collection<Vol> volArrivee;

}
