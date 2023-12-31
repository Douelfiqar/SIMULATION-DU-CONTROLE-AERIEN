package com.example.simulationducontroleaerien.services;

import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportRequest;
import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportResponse;

import java.util.List;

public interface AeroportService {
    public List<AeroportResponse> lisAeroport();
    public AeroportResponse addAeroport(AeroportRequest aeroportRequest);
    public AeroportResponse getAeroportByName(String name);
    public AeroportResponse updateAeroport(AeroportRequest aeroportRequest);
    public void removeAeroportByName(String name);
}
