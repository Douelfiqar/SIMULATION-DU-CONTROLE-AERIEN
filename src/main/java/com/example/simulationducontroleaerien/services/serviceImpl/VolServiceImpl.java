package com.example.simulationducontroleaerien.services.serviceImpl;

import com.example.simulationducontroleaerien.DTOs.VolDtos.VolRequest;
import com.example.simulationducontroleaerien.DTOs.VolDtos.VolResponse;
import com.example.simulationducontroleaerien.DTOs.escaleDtos.EscaleRequest;
import com.example.simulationducontroleaerien.entities.Aeroport;
import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.Escale;
import com.example.simulationducontroleaerien.entities.Vol;
import com.example.simulationducontroleaerien.mappers.avion.AvionMapper;
import com.example.simulationducontroleaerien.mappers.escale.EscaleMapper;
import com.example.simulationducontroleaerien.mappers.vol.VolMapper;
import com.example.simulationducontroleaerien.repositories.AeroportRepository;
import com.example.simulationducontroleaerien.repositories.AvionRepository;
import com.example.simulationducontroleaerien.repositories.EscaleRepository;
import com.example.simulationducontroleaerien.repositories.VolRepository;
import com.example.simulationducontroleaerien.services.AvionService;
import com.example.simulationducontroleaerien.services.VolService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class VolServiceImpl implements VolService {
    private VolRepository volRepository;
    private AeroportRepository aeroportRepository;
    private AvionRepository avionRepository;
    private EscaleRepository escaleRepository;
    private VolMapper volMapper;
    private EscaleMapper escaleMapper;
    @Override
    public List<VolResponse> findAll() {
        List<Vol> volList = volRepository.findAll();
        List<VolResponse> volResponses = volList.stream()
                .map(vol -> volMapper.VolToVolResponse(vol))
                .collect(Collectors.toList());
        return volResponses;
    }

    @Override
    public VolResponse addVol(VolRequest volRequest) {

        Aeroport aeroportDepart = aeroportRepository.findAeroportByName(volRequest.nameAeroportDepart());
        Aeroport aeroportArrive = aeroportRepository.findAeroportByName(volRequest.nameAeroportArrive());
        Avion avion = avionRepository.findAvionByNumeroSerie(volRequest.numeroSerieAvion());

        // ADD STATIC HOUR FOR TEST
        Date currentDate = new Date();

        // Add 2 hours (2 * 60 * 60 * 1000 milliseconds)
        long twoHoursInMilliseconds = 2 * 60 * 60 * 1000;
        long updatedTimeMillis = currentDate.getTime() + twoHoursInMilliseconds;

        // Create a new Date object with the updated time
        Date updatedDate = new Date(updatedTimeMillis);

        // i need List of Escale


       Vol vol = Vol.builder()
                .heurDepart(volRequest.heurDepart())
                .heurArriver(updatedDate)
                .aeroportDepart(aeroportDepart)
                .aeroportArrivee(aeroportArrive)
                .avion(avion)
                .build();

/*
        if(escaleRequest != null){
            List<Escale> escaleList = new ArrayList<>();
            Escale escale = escaleMapper.escaleRequestToEscale(escaleRequest);
            escaleList.add(escale);
            vol.setEscale(escaleList);
            escaleRepository.save(escale);
        }*/

        volRepository.save(vol);
        VolResponse volResponse = volMapper.VolToVolResponse(vol);
       return volResponse;
    }

    @Override
    public VolResponse getVolById(int id) {
        Vol vol = volRepository.findById(id).orElseThrow();
        VolResponse volResponse = volMapper.VolToVolResponse(vol);

        return volResponse;
    }

    @Override
    public VolResponse updateVol(VolRequest volRequest, int id, Escale escale) {
        Vol vol = volRepository.findById(id).orElseThrow();
        Aeroport aeroportDepart = aeroportRepository.findAeroportByName(volRequest.nameAeroportDepart());
        Aeroport aeroportArrive = aeroportRepository.findAeroportByName(volRequest.nameAeroportArrive());
        Avion avion = avionRepository.findAvionByNumeroSerie(volRequest.numeroSerieAvion());

        Vol vol1 = Vol.builder()
                .aeroportArrivee(aeroportDepart)
                .aeroportArrivee(aeroportArrive)
                .heurDepart(volRequest.heurDepart())
                .avion(avion)
                .build();

        if(escale != null)
        {
            Collection<Escale> escaleCollection = vol1.getEscale();
            escaleCollection.add(escale);
            vol1.setEscale(escaleCollection);
        }

        VolResponse volResponse = volMapper.VolToVolResponse(vol1);
        return volResponse;
    }

    @Override
    public void removeVolById(int id) {
        Vol vol = volRepository.findById(id).orElseThrow();
        volRepository.deleteById(id);
    }
}
