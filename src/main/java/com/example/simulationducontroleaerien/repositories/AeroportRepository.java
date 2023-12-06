package com.example.simulationducontroleaerien.repositories;

import com.example.simulationducontroleaerien.entities.Aeroport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AeroportRepository extends JpaRepository<Aeroport, Integer> {
    public Aeroport findAeroportByName(String name);
    public void deleteAeroportByName(String name);
}
