package com.example.simulationducontroleaerien.repositories;

import com.example.simulationducontroleaerien.entities.Escale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscaleRepository extends JpaRepository<Escale, Integer> {
}
