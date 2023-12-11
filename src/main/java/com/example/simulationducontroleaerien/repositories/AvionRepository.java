package com.example.simulationducontroleaerien.repositories;

import com.example.simulationducontroleaerien.entities.Avion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvionRepository extends JpaRepository<Avion, Integer> {
    public Avion findAvionByNumeroSerie(String numeroSerie);
    public void deleteAvionByNumeroSerie(String numeroSerie);
}
