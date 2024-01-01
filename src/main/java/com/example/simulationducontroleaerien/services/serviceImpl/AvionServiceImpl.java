package com.example.simulationducontroleaerien.services.serviceImpl;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionRequest;
import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionResponse;
import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;
import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.TypeAvion;
import com.example.simulationducontroleaerien.exceptions.TypeAvionNotExist;
import com.example.simulationducontroleaerien.mappers.avion.AvionMapper;
import com.example.simulationducontroleaerien.mappers.typeAvion.TypeAvionMapper;
import com.example.simulationducontroleaerien.repositories.AvionRepository;
import com.example.simulationducontroleaerien.repositories.TypeAvionRepository;
import com.example.simulationducontroleaerien.services.AvionService;
import com.example.simulationducontroleaerien.services.TypeAvionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class AvionServiceImpl implements AvionService {
    private AvionRepository avionRepository;
    private TypeAvionRepository typeAvionRepository;
    private TypeAvionService typeAvionService;

    @Override
    public List<AvionResponse> listAvions() {
        List<Avion> avion = avionRepository.findAll();

        List<AvionResponse> avionResponseList = avion.stream()
                .map(avionStream -> AvionMapper.AvionToAvionResponse(avionStream))
                .collect(Collectors.toList());

        return avionResponseList;
    }

    @Override
    public AvionResponse addAvion(AvionRequest avionRequest) throws TypeAvionNotExist{
        Avion avion = AvionMapper.avionRequestToAvion(avionRequest);

        TypeAvionDto typeAvionDTO = typeAvionService.getTypeAvionByName(avionRequest.nameTypeAvionDto());

        TypeAvion typeAvion1 = typeAvionRepository.findTypeAvionByName(avionRequest.nameTypeAvionDto());
        avion.setTypeAvion(typeAvion1);
       // System.out.println(typeAvion1);
        //System.out.println(avion);
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
    public AvionResponse updateAvion(AvionRequest avionRequest, String numeroAvion) {
        TypeAvion typeAvion = typeAvionRepository.findTypeAvionByName(avionRequest.nameTypeAvionDto());
        if(typeAvion == null)
            return null;
        Avion avion = avionRepository.findAvionByNumeroSerie(numeroAvion);

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
