package com.example.simulationducontroleaerien.mappers.avion;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionRequest;
import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionResponse;
import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;
import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.TypeAvion;
import com.example.simulationducontroleaerien.exceptions.TypeAvionNotExist;
import com.example.simulationducontroleaerien.mappers.typeAvion.TypeAvionMapper;
import com.example.simulationducontroleaerien.repositories.TypeAvionRepository;
import com.example.simulationducontroleaerien.services.TypeAvionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AvionMapper {

    public static Avion avionRequestToAvion(AvionRequest avionRequest) {

        var Uuid = UUID.randomUUID().toString();

        Avion avion = Avion.builder()
                .nameAvion(avionRequest.name())
                .numeroSerie(Uuid)
                .build();

        return avion;
    }
    public static AvionResponse AvionToAvionResponse(Avion avion){
        TypeAvionDto typeAvionDto = TypeAvionMapper.TypeAvionToTypeAvionDto(avion.getTypeAvion());

        AvionResponse avionResponse = AvionResponse.builder()
                .id(avion.getId())
                .typeAvionDto(typeAvionDto)
                .numeroSerie(avion.getNumeroSerie())
                .name(avion.getNameAvion())
                .typeAvionDto(TypeAvionMapper.TypeAvionToTypeAvionDto(avion.getTypeAvion()))
                .build();

        return avionResponse;
    }
}
