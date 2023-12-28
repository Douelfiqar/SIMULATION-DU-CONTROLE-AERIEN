package com.example.simulationducontroleaerien.mappers.escale;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionRequest;
import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionResponse;
import com.example.simulationducontroleaerien.DTOs.escaleDtos.EscaleRequest;
import com.example.simulationducontroleaerien.DTOs.escaleDtos.EscaleResponse;
import com.example.simulationducontroleaerien.entities.Aeroport;
import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.Escale;
import com.example.simulationducontroleaerien.entities.Vol;
import com.example.simulationducontroleaerien.mappers.aeroport.AeroportMapper;
import com.example.simulationducontroleaerien.mappers.vol.VolMapper;
import com.example.simulationducontroleaerien.repositories.AeroportRepository;
import com.example.simulationducontroleaerien.repositories.EscaleRepository;
import com.example.simulationducontroleaerien.repositories.VolRepository;
import com.example.simulationducontroleaerien.services.serviceImpl.VolServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EscaleMapper {
    private AeroportRepository aeroportRepository;
    private VolRepository volRepository;
    private VolMapper volMapper;
    public Escale escaleRequestToEscale(EscaleRequest escaleRequest){
        Aeroport aeroport = aeroportRepository.findAeroportByName(escaleRequest.nameAeroport());
        Vol vol = volRepository.findById(escaleRequest.idVol()).get();

        Escale escale = Escale.builder()
                .dateEscale(escaleRequest.dateEscale())
                .aeroport(aeroport)
                .vol(vol)
                .build();

        return escale;
    }
    public EscaleResponse escaleToEscaleResponse(Escale escale){
        EscaleResponse escaleResponse = EscaleResponse.builder()
                .idEscale(escale.getId())
                .dateEscale(escale.getDateEscale())
                .vol(volMapper.VolToVolResponse(escale.getVol()))
                .aeroportResponse(AeroportMapper.AeroportToAeroportResponse(escale.getAeroport()))
                .build();

        return escaleResponse;
    }
}
