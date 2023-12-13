package com.example.simulationducontroleaerien.services;

import java.util.List;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionRequest;
import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionResponse;
import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportRequest;
import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportResponse;
import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.TypeAvion;

public interface AvionService {
    public AvionResponse addAvion(AvionRequest avionRequest);
    public AvionResponse getAvionByNumeroSerie(String numeroSerie);
    public AvionResponse updateAvion(AvionRequest avionRequest);
    public void removeAvionByName(String name);
    public List<Avion> getAvionsByType(TypeAvion typeAvion);
}
