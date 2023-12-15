package com.example.simulationducontroleaerien.entities;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    @ElementCollection
    private Map<Aeroport, Double> distanceAuxAutresAeroports;

    @OneToMany
    private List<Escale> escale;
    @OneToMany(mappedBy = "aeroportDepart")
    private Collection<Vol> volDepart;
    @OneToMany(mappedBy = "aeroportArrivee")
    private Collection<Vol> volArrivee;
	

}
