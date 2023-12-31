package com.example.simulationducontroleaerien.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Avion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "numeroSerie", unique = true)
    private String numeroSerie;
    private String nameAvion;

    @ManyToOne
    @JsonBackReference
    private TypeAvion typeAvion;
    @OneToMany
    @JsonBackReference
    private Collection<Vol> vol;
}
