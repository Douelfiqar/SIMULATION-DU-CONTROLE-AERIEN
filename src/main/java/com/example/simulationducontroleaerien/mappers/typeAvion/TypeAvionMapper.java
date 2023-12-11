package com.example.simulationducontroleaerien.mappers.typeAvion;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionRequest;
import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionResponse;
import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;
import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.TypeAvion;

public class TypeAvionMapper {
    public static TypeAvion typeAvionDtoToTypeAvion(TypeAvionDto typeAvionDto){
        TypeAvion typeAvion = TypeAvion.builder()
                .name(typeAvionDto.name())
                .consomationNormale(typeAvionDto.consomationNormale())
                .consomationBoucleAttente(typeAvionDto.consomationBoucleAttente())
                .vitesseBoucleAttente(typeAvionDto.vitesseBoucleAttente())
                .vitesseNormale(typeAvionDto.vitesseNormale())
                .build();

        return typeAvion;
    }
    public static TypeAvionDto TypeAvionToTypeAvionDto(TypeAvion typeAvion){
        TypeAvionDto typeAvionDto = TypeAvionDto.builder()
                .name(typeAvion.getName())
                .consomationNormale(typeAvion.getConsomationNormale())
                .consomationBoucleAttente(typeAvion.getConsomationBoucleAttente())
                .vitesseBoucleAttente(typeAvion.getVitesseBoucleAttente())
                .vitesseNormale(typeAvion.getVitesseNormale())
                .build();

        return typeAvionDto;
    }
}
