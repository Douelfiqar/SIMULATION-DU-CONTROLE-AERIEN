package com.example.simulationducontroleaerien.mappers.avion;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionRequest;
import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionResponse;
import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.TypeAvion;
import com.example.simulationducontroleaerien.mappers.typeAvion.TypeAvionMapper;
import com.example.simulationducontroleaerien.repositories.TypeAvionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AvionMapper {
    private static TypeAvionRepository typeAvionRepository;
    public static Avion avionRequestToAvion(AvionRequest avionRequest){
        TypeAvion typeAvion = typeAvionRepository.findTypeAvionByName(avionRequest.nameTypeAvionDto());

        Avion avion = Avion.builder()
                .typeAvion(typeAvion)
                .numeroSerie(avionRequest.numeroSerie())
                .build();

        return avion;
    }
    public static AvionResponse AvionToAvionResponse(Avion avion){
        AvionResponse avionResponse = AvionResponse.builder()
                .numeroSerie(avion.getNumeroSerie())
                .typeAvionDto(TypeAvionMapper.TypeAvionToTypeAvionDto(avion.getTypeAvion()))
                .build();

        return avionResponse;
    }
}
