package com.example.simulationducontroleaerien.services.serviceImpl;

import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;
import com.example.simulationducontroleaerien.repositories.TypeAvionRepository;
import com.example.simulationducontroleaerien.services.TypeAvionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TypeAvionServiceImpl implements TypeAvionService {
    private TypeAvionRepository typeAvionRepository;
    @Override
    public TypeAvionDto addTypeAvion(TypeAvionDto aeroportRequest) {
        return null;
    }

    @Override
    public TypeAvionDto getTypeAvionByName(String name) {
        return null;
    }

    @Override
    public TypeAvionDto updateTypeAvion(TypeAvionDto aeroportRequest) {
        return null;
    }

    @Override
    public void removeTypeAvionByName(String name) {

    }
}
