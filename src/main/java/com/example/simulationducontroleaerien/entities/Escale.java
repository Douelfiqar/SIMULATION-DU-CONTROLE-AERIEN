package com.example.simulationducontroleaerien.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Escale {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dateEscale;
    @ManyToOne
    private Aeroport aeroport;
    @ManyToOne
    private Vol vol;
}
