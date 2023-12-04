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
public class Vol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date heurDepart;
    private Date heurArriver;
    @ManyToOne
    private Escale escale;
    @ManyToOne
    private Aeroport aeroportDepart;
    @ManyToOne
    private Aeroport aeroportArrivee;
}
