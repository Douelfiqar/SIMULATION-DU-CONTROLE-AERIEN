package com.example.simulationducontroleaerien.services;

import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;
import com.example.simulationducontroleaerien.exceptions.NameTypeAvionExist;

public interface TypeAvionService {
    public TypeAvionDto addTypeAvion(TypeAvionDto aeroportRequest) throws NameTypeAvionExist;
    public TypeAvionDto getTypeAvionByName(String name);
    public TypeAvionDto updateTypeAvion(TypeAvionDto typeAvionDto);
    public void removeTypeAvionByName(String name);
}
