package com.example.simulationducontroleaerien.services;


import java.util.List;

import com.example.simulationducontroleaerien.DTOs.VolDtos.VolRequest;
import com.example.simulationducontroleaerien.DTOs.VolDtos.VolResponse;
import com.example.simulationducontroleaerien.DTOs.escaleDtos.EscaleRequest;
import com.example.simulationducontroleaerien.entities.Aeroport;
import com.example.simulationducontroleaerien.entities.Escale;

public interface VolService {
    public VolResponse addVol(VolRequest volRequest, EscaleRequest escaleRequest);
    public VolResponse getVolById(int id);
    public VolResponse updateVol(VolRequest volRequest, int id, Escale escale);
    public void removeVolById(int id);
    public List<Aeroport> getShortestPath(int id);
}
