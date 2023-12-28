package com.example.simulationducontroleaerien.services.serviceImpl;

import com.example.simulationducontroleaerien.DTOs.escaleDtos.EscaleRequest;
import com.example.simulationducontroleaerien.DTOs.escaleDtos.EscaleResponse;
import com.example.simulationducontroleaerien.entities.Aeroport;
import com.example.simulationducontroleaerien.entities.Escale;
import com.example.simulationducontroleaerien.entities.Vol;
import com.example.simulationducontroleaerien.mappers.escale.EscaleMapper;
import com.example.simulationducontroleaerien.repositories.AeroportRepository;
import com.example.simulationducontroleaerien.repositories.EscaleRepository;
import com.example.simulationducontroleaerien.repositories.VolRepository;
import com.example.simulationducontroleaerien.services.EscaleService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class EscaleServiceImpl implements EscaleService {
    private EscaleRepository escaleRepository;
    private AeroportRepository aeroportRepository;
    private VolRepository volRepository;
    private EscaleMapper escaleMapper;
    @Override
    public List<EscaleResponse> lisEscale() {
        List<Escale> escaleList = escaleRepository.findAll();
        List<EscaleResponse> escaleResponseList = escaleList.stream()
                .map(escale -> escaleMapper.escaleToEscaleResponse(escale))
                .collect(Collectors.toList());

        return escaleResponseList;
    }

    @Override
    public EscaleResponse addEscale(EscaleRequest escaleRequest) {
        Aeroport aeroport = aeroportRepository.findAeroportByName(escaleRequest.nameAeroport());
        Vol vol = volRepository.findById(escaleRequest.idVol()).orElseThrow();
        Escale escale = Escale.builder()
                .aeroport(aeroport)
                .dateEscale(escaleRequest.dateEscale())
                .vol(vol)
                .build();
        escaleRepository.save(escale);
        EscaleResponse escaleResponse = escaleMapper.escaleToEscaleResponse(escale);
        return escaleResponse;
    }

    @Override
    public EscaleResponse getEscaleById(int id) {
        Escale escale = escaleRepository.findById(id).orElseThrow();
        EscaleResponse escaleResponse = escaleMapper.escaleToEscaleResponse(escale);
        return escaleResponse;
    }

    @Override
    public EscaleResponse updateEscale(EscaleRequest escaleRequest, int id) {
        Escale escale = escaleRepository.findById(id).orElseThrow();
        Aeroport aeroport = aeroportRepository.findAeroportByName(escaleRequest.nameAeroport());
        Vol vol = volRepository.findById(escaleRequest.idVol()).orElseThrow();

        escale.setAeroport(aeroport);
        escale.setVol(vol);
        escale.setDateEscale(escaleRequest.dateEscale());

        escaleRepository.save(escale);
        EscaleResponse escaleResponse = escaleMapper.escaleToEscaleResponse(escale);
        return escaleResponse;
    }

    @Override
    public void removeEscaleById(int id) {
        Escale escale = escaleRepository.findById(id).orElseThrow();
        escaleRepository.deleteById(id);
    }
}
