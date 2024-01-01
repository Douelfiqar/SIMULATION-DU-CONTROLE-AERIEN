package com.example.simulationducontroleaerien.services;

import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;
import com.example.simulationducontroleaerien.DTOs.escaleDtos.EscaleRequest;
import com.example.simulationducontroleaerien.DTOs.escaleDtos.EscaleResponse;

import java.util.List;

public interface EscaleService {
    public List<EscaleResponse> lisEscale();
    public EscaleResponse addEscale(EscaleRequest escaleRequest);
    public EscaleResponse getEscaleById(int id);
    public EscaleResponse updateEscale(EscaleRequest escaleRequest, int id);
    public void removeEscaleById(int id);
}
