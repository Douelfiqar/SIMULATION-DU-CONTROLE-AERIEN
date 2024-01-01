package com.example.simulationducontroleaerien.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @OneToMany(mappedBy = "vol",fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<Escale> escale;
    @ManyToOne
    @JsonManagedReference
    private Aeroport aeroportDepart;
    @ManyToOne
    @JsonManagedReference
    private Aeroport aeroportArrivee;
    @ManyToOne
    @JsonManagedReference
    private Avion avion;
}
