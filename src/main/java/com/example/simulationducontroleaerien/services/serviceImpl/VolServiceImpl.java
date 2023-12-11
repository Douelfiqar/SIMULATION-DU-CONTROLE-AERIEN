package com.example.simulationducontroleaerien.services.serviceImpl;

import com.example.simulationducontroleaerien.DTOs.VolDtos.VolRequest;
import com.example.simulationducontroleaerien.DTOs.VolDtos.VolResponse;
import com.example.simulationducontroleaerien.repositories.VolRepository;
import com.example.simulationducontroleaerien.services.VolService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VolServiceImpl implements VolService {
    private VolRepository volRepository;
    @Override
    public VolResponse addVol(VolRequest volRequest) {
        return null;
    }

    @Override
    public VolResponse getVolByName(String name) {
        return null;
    }

    @Override
    public VolResponse updateVol(VolRequest volRequest) {
        return null;
    }

    @Override
    public void removeVolByName(String name) {

    }
}
