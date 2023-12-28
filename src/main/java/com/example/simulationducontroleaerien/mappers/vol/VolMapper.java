package com.example.simulationducontroleaerien.mappers.vol;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionRequest;
import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionResponse;
import com.example.simulationducontroleaerien.DTOs.VolDtos.VolRequest;
import com.example.simulationducontroleaerien.DTOs.VolDtos.VolResponse;
import com.example.simulationducontroleaerien.entities.Aeroport;
import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.Vol;
import com.example.simulationducontroleaerien.mappers.aeroport.AeroportMapper;
import com.example.simulationducontroleaerien.mappers.avion.AvionMapper;
import com.example.simulationducontroleaerien.repositories.AeroportRepository;
import com.example.simulationducontroleaerien.repositories.AvionRepository;
import com.example.simulationducontroleaerien.repositories.EscaleRepository;
import com.example.simulationducontroleaerien.repositories.VolRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VolMapper {
    private AvionRepository avionRepository;
    private static AeroportRepository aeroportRepository;

    public Vol volRequestToVol(VolRequest volRequest){
        Aeroport aeroportDepart = aeroportRepository.findAeroportByName(volRequest.nameAeroportDepart());
        Aeroport aeroportArrivee = aeroportRepository.findAeroportByName(volRequest.nameAeroportArrive());

        Avion avion = avionRepository.findAvionByNumeroSerie(volRequest.numeroSerieAvion());



        Vol vol = Vol.builder()
                .heurDepart(volRequest.heurDepart())
                .heurArriver(volRequest.heurArriver())
                .aeroportArrivee(aeroportDepart)
                .aeroportDepart(aeroportArrivee)
                .avion(avion)
                .build();

        return vol;
    }
    public VolResponse VolToVolResponse(Vol vol){
        AvionResponse avionResponse = AvionMapper.AvionToAvionResponse(vol.getAvion());

        VolResponse volResponse = VolResponse.builder()
                .aeroportDepart(AeroportMapper.AeroportToAeroportResponse(vol.getAeroportDepart()))
                .aeroportArrive(AeroportMapper.AeroportToAeroportResponse(vol.getAeroportArrivee()))
                .heurArriver(vol.getHeurArriver())
                .heurDepart(vol.getHeurDepart())
                .avionResponse(avionResponse)
                //.escale()
                .build();

        return volResponse;
    }
}
