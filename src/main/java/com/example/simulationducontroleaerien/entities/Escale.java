package com.example.simulationducontroleaerien.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
