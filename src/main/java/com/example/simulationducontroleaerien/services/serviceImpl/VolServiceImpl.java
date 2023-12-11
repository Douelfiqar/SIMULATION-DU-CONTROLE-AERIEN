package com.example.simulationducontroleaerien.services.serviceImpl;

import com.example.simulationducontroleaerien.DTOs.VolDtos.VolRequest;
import com.example.simulationducontroleaerien.DTOs.VolDtos.VolResponse;
import com.example.simulationducontroleaerien.DTOs.escaleDtos.EscaleRequest;
import com.example.simulationducontroleaerien.entities.Aeroport;
import com.example.simulationducontroleaerien.entities.Escale;
import com.example.simulationducontroleaerien.entities.Vol;
import com.example.simulationducontroleaerien.mappers.escale.EscaleMapper;
import com.example.simulationducontroleaerien.mappers.vol.VolMapper;
import com.example.simulationducontroleaerien.repositories.AeroportRepository;
import com.example.simulationducontroleaerien.repositories.VolRepository;
import com.example.simulationducontroleaerien.services.VolService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VolServiceImpl implements VolService {
    private VolRepository volRepository;
    private AeroportRepository aeroportRepository;
    @Override
    public VolResponse addVol(VolRequest volRequest, EscaleRequest escaleRequest) {
        Aeroport aeroportDepart = aeroportRepository.findAeroportByName(volRequest.nameAeroportDepart());
        Aeroport aeroportArrive = aeroportRepository.findAeroportByName(volRequest.nameAeroportArrive());

        Vol vol = Vol.builder()
                .heurDepart(volRequest.heurDepart())
                .heurArriver(volRequest.heurArriver())
                .aeroportDepart(aeroportDepart)
                .aeroportArrivee(aeroportArrive)
                .build();
        if(escaleRequest != null){
            Escale escale = EscaleMapper.escaleRequestToEscale(escaleRequest);
            vol.setEscale(escale);
        }
        volRepository.save(vol);
        VolResponse volResponse = VolMapper.VolToVolResponse(vol);
        return volResponse;
    }

    @Override
    public VolResponse getVolById(int id) {
        Vol vol = volRepository.findById(id).orElseThrow();
        VolResponse volResponse = VolMapper.VolToVolResponse(vol);

        return volResponse;
    }

    @Override
    public VolResponse updateVol(VolRequest volRequest, int id, Escale escale) {
        Vol vol = volRepository.findById(id).orElseThrow();
        Aeroport aeroportDepart = aeroportRepository.findAeroportByName(volRequest.nameAeroportDepart());
        Aeroport aeroportArrive = aeroportRepository.findAeroportByName(volRequest.nameAeroportArrive());

        Vol vol1 = Vol.builder()
                .aeroportArrivee(aeroportDepart)
                .aeroportArrivee(aeroportArrive)
                .heurArriver(volRequest.heurArriver())
                .heurDepart(volRequest.heurDepart())
                .build();

        if(escale != null)
            vol1.setEscale(escale);

        VolResponse volResponse = VolMapper.VolToVolResponse(vol1);
        return volResponse;
    }

    @Override
    public void removeVolById(int id) {
        Vol vol = volRepository.findById(id).orElseThrow();
        volRepository.deleteById(id);
    }
}
