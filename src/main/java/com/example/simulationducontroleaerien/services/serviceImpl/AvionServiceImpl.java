package com.example.simulationducontroleaerien.services.serviceImpl;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionRequest;
import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionResponse;
import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.TypeAvion;
import com.example.simulationducontroleaerien.mappers.avion.AvionMapper;
import com.example.simulationducontroleaerien.repositories.AvionRepository;
import com.example.simulationducontroleaerien.repositories.TypeAvionRepository;
import com.example.simulationducontroleaerien.services.AvionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AvionServiceImpl implements AvionService {
    private AvionRepository avionRepository;
    private TypeAvionRepository typeAvionRepository;
    @Override
    public AvionResponse addAvion(AvionRequest avionRequest) {
        Avion avion = AvionMapper.avionRequestToAvion(avionRequest);
        avionRepository.save(avion);
        AvionResponse avionResponse = AvionMapper.AvionToAvionResponse(avion);
        return avionResponse;
    }

    @Override
    public AvionResponse getAvionByNumeroSerie(String name) {
        Avion avion = avionRepository.findAvionByNumeroSerie(name);
        if(avion == null)
            return null;

        AvionResponse avionResponse = AvionMapper.AvionToAvionResponse(avion);
        return avionResponse;
    }

    @Override
    public AvionResponse updateAvion(AvionRequest avionRequest) {
        TypeAvion typeAvion = typeAvionRepository.findTypeAvionByName(avionRequest.nameTypeAvionDto());
        if(typeAvion == null)
            return null;
        Avion avion = avionRepository.findAvionByNumeroSerie(avionRequest.numeroSerie());

        if(avion == null)
            return null;

        avion.setTypeAvion(typeAvion);

        AvionResponse avionResponse = AvionMapper.AvionToAvionResponse(avion);
        return avionResponse;
    }

    @Override
    public void removeAvionByName(String numeroSerie) {
        Avion avion = avionRepository.findAvionByNumeroSerie(numeroSerie);
        if(avion != null)
            avionRepository.deleteAvionByNumeroSerie(numeroSerie);
    }
}
