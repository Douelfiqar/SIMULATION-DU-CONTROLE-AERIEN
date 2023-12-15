package com.example.simulationducontroleaerien.mappers.vol;

import org.springframework.stereotype.Component;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionResponse;
import com.example.simulationducontroleaerien.DTOs.VolDtos.VolRequest;
import com.example.simulationducontroleaerien.DTOs.VolDtos.VolResponse;
import com.example.simulationducontroleaerien.entities.Aeroport;
import com.example.simulationducontroleaerien.entities.Vol;
import com.example.simulationducontroleaerien.mappers.aeroport.AeroportMapper;
import com.example.simulationducontroleaerien.mappers.avion.AvionMapper;
import com.example.simulationducontroleaerien.repositories.AeroportRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class VolMapper {
    private static AeroportRepository aeroportRepository;
    public static Vol volRequestToVol(VolRequest volRequest){
        Aeroport aeroportDepart = aeroportRepository.findAeroportByName(volRequest.nameAeroportDepart());
        Aeroport aeroportArrivee = aeroportRepository.findAeroportByName(volRequest.nameAeroportArrive());

        Vol vol = Vol.builder()
                .heurDepart(volRequest.heurDepart())
                .heurArriver(volRequest.heurArriver())
                //.avion(AvionMapper.avionRequestToAvion(volRequest.avion()))
                .aeroportArrivee(aeroportDepart)
                .aeroportDepart(aeroportArrivee)
                .build();

        return vol;
    }
    public static VolResponse VolToVolResponse(Vol vol){
        VolResponse volResponse = VolResponse.builder()
                .aeroportDepart(AeroportMapper.AeroportToAeroportResponse(vol.getAeroportDepart()))
                .aeroportArrive(AeroportMapper.AeroportToAeroportResponse(vol.getAeroportArrivee()))
                .heurArriver(vol.getHeurArriver())
                .heurDepart(vol.getHeurDepart())
                .avion(AvionMapper.AvionToAvionResponse(vol.getAvion()))
                .build();

        return volResponse;
    }
}
