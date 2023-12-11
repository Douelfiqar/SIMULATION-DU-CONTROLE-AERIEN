package com.example.simulationducontroleaerien.services;


import com.example.simulationducontroleaerien.DTOs.VolDtos.VolRequest;
import com.example.simulationducontroleaerien.DTOs.VolDtos.VolResponse;

public interface VolService {
    public VolResponse addVol(VolRequest volRequest);
    public VolResponse getVolByName(String name);
    public VolResponse updateVol(VolRequest volRequest);
    public void removeVolByName(String name);
}
