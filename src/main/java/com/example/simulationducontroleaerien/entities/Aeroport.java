package com.example.simulationducontroleaerien.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @ElementCollection(fetch = FetchType.EAGER)
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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hash(idAeroport);
        return result;
    }
}
