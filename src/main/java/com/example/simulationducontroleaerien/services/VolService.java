package com.example.simulationducontroleaerien.services;


import com.example.simulationducontroleaerien.DTOs.VolDtos.VolRequest;
import com.example.simulationducontroleaerien.DTOs.VolDtos.VolResponse;
import com.example.simulationducontroleaerien.DTOs.escaleDtos.EscaleRequest;
import com.example.simulationducontroleaerien.entities.Escale;

import java.util.List;

public interface VolService {
    public List<VolResponse> findAll();
    public VolResponse addVol(VolRequest volRequest, EscaleRequest escaleRequest);
    public VolResponse getVolById(int id);
    public VolResponse updateVol(VolRequest volRequest, int id, Escale escale);
    public void removeVolById(int id);
}
