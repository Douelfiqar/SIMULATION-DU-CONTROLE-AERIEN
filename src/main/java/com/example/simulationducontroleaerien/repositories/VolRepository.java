package com.example.simulationducontroleaerien.repositories;

import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.Vol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolRepository extends JpaRepository<Vol, Integer> {

}
