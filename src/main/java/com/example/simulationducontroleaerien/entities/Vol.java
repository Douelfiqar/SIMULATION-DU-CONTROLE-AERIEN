package com.example.simulationducontroleaerien.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
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
    @OneToMany(mappedBy = "vol")
    private Collection<Escale> escale = new ArrayList<>();
    @ManyToOne
    private Aeroport aeroportDepart;
    @ManyToOne
    private Aeroport aeroportArrivee;
    @ManyToOne
    private Avion avion;
}
