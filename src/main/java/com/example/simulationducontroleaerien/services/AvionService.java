package com.example.simulationducontroleaerien.services;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionRequest;
import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionResponse;
import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportRequest;
import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportResponse;

public interface AvionService {
    public AvionResponse addAvion(AvionRequest avionRequest);
    public AvionResponse getAvionByName(String name);
    public AvionResponse updateAvion(AvionRequest avionRequest);
    public void removeAvionByName(String name);
}
