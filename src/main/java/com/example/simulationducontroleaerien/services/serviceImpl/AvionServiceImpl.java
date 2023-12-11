package com.example.simulationducontroleaerien.services.serviceImpl;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionRequest;
import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionResponse;
import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportResponse;
import com.example.simulationducontroleaerien.repositories.AvionRepository;
import com.example.simulationducontroleaerien.services.AvionService;
import org.springframework.stereotype.Service;

@Service
public class AvionServiceImpl implements AvionService {
    private AvionRepository avionRepository;
    @Override
    public AvionResponse addAvion(AvionRequest avionRequest) {
        return null;
    }

    @Override
    public AvionResponse getAvionByName(String name) {
        return null;
    }

    @Override
    public AvionResponse updateAvion(AvionRequest avionRequest) {
        return null;
    }

    @Override
    public void removeAvionByName(String name) {

    }
}
