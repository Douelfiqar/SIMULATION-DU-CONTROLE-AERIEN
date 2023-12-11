package com.example.simulationducontroleaerien.services.serviceImpl;

import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;
import com.example.simulationducontroleaerien.entities.TypeAvion;
import com.example.simulationducontroleaerien.mappers.typeAvion.TypeAvionMapper;
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
        TypeAvion typeAvion = TypeAvion.builder()
                .vitesseNormale(aeroportRequest.vitesseNormale())
                .vitesseBoucleAttente(aeroportRequest.vitesseBoucleAttente())
                .consomationBoucleAttente(aeroportRequest.consomationBoucleAttente())
                .consomationNormale(aeroportRequest.consomationNormale())
                .build();

        typeAvionRepository.save(typeAvion);

        TypeAvionDto typeAvionDto = TypeAvionMapper.TypeAvionToTypeAvionDto(typeAvion);

        return typeAvionDto;
    }

    @Override
    public TypeAvionDto getTypeAvionByName(String name) {
        TypeAvion typeAvion = typeAvionRepository.findTypeAvionByName(name);
        if(typeAvion == null)
            return null;

        TypeAvionDto typeAvionDto = TypeAvionMapper.TypeAvionToTypeAvionDto(typeAvion);
        return typeAvionDto;
    }

    @Override
    public TypeAvionDto updateTypeAvion(TypeAvionDto typeAvionDto) {
        TypeAvion typeAvion = typeAvionRepository.findTypeAvionByName(typeAvionDto.name());
        if(typeAvion == null)
            return null;

        TypeAvion typeAvion1 = TypeAvion.builder()
                .vitesseNormale(typeAvionDto.vitesseNormale())
                .consomationNormale(typeAvion.getConsomationNormale())
                .vitesseBoucleAttente(typeAvion.getVitesseBoucleAttente())
                .consomationBoucleAttente(typeAvionDto.consomationBoucleAttente())
                .build();
        TypeAvionDto typeAvionDto1 = TypeAvionMapper.TypeAvionToTypeAvionDto(typeAvion);
        return typeAvionDto1;
    }

    @Override
    public void removeTypeAvionByName(String name) {
        TypeAvion typeAvion = typeAvionRepository.findTypeAvionByName(name);
        if(typeAvion!=null)
            typeAvionRepository.removeTypeAvionByName(name);
    }
}
