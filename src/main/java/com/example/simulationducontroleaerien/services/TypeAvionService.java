package com.example.simulationducontroleaerien.services;

import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;
import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportRequest;
import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportResponse;

public interface TypeAvionService {
    public TypeAvionDto addTypeAvion(TypeAvionDto aeroportRequest);
    public TypeAvionDto getTypeAvionByName(String name);
    public TypeAvionDto updateTypeAvion(TypeAvionDto typeAvionDto);
    public void removeTypeAvionByName(String name);
}
