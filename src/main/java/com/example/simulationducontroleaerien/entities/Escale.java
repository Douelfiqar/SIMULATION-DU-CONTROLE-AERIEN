package com.example.simulationducontroleaerien.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    private Aeroport aeroport;
    @ManyToOne
    @JsonBackReference
    private Vol vol;
}
