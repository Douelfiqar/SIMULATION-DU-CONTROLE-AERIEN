package com.example.simulationducontroleaerien.repositories;

import com.example.simulationducontroleaerien.entities.Vol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolRepository extends JpaRepository<Vol, Integer> {
}
