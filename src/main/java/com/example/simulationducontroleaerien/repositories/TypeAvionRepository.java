package com.example.simulationducontroleaerien.repositories;

import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.TypeAvion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAvionRepository extends JpaRepository<TypeAvion, Integer> {
    public TypeAvion findTypeAvionByName(String name);
}
