package com.example.simulationducontroleaerien.services.serviceImpl;

import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportRequest;
import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportResponse;
import com.example.simulationducontroleaerien.entities.Aeroport;
import com.example.simulationducontroleaerien.mappers.aeroport.AeroportMapper;
import com.example.simulationducontroleaerien.repositories.AeroportRepository;
import com.example.simulationducontroleaerien.services.AeroportService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class AeroportServiceImpl implements AeroportService {
    private AeroportRepository aeroportRepository;

    @Override
    public List<AeroportResponse> lisAeroport() {
        List<Aeroport> aeroportList = aeroportRepository.findAll();
        List<AeroportResponse> aeroportResponsesList = aeroportList.stream()
                .map(aeroport -> AeroportMapper.AeroportToAeroportResponse(aeroport))
                .collect(Collectors.toList());

        return aeroportResponsesList;
    }

    @Override
    public AeroportResponse addAeroport(AeroportRequest aeroportRequest) {
        Aeroport aeroport = Aeroport.builder()
                .delaiAttenteAuSol(aeroportRequest.delaiAttenteAuSol())
                .delaiAntiCollision(aeroportRequest.delaiAntiCollision())
                .dureeBoucleAttente(aeroportRequest.dureeBoucleAttente())
                .localisation(aeroportRequest.localisation())
                .name(aeroportRequest.name())
                .nombreDePlaceAuSol(aeroportRequest.nombreDePlaceAuSol())
                .nombrePistes(aeroportRequest.nombrePistes())
                .tempsAccessAuxPistes(aeroportRequest.tempsAccessAuxPistes())
                .tempsDecollageAtterrissage(aeroportRequest.tempsDecollageAtterrissage())
                .x(aeroportRequest.x())
                .y(aeroportRequest.y())
                .build();
        aeroportRepository.save(aeroport);
        AeroportResponse aeroportResponse = AeroportMapper.AeroportToAeroportResponse(aeroport);

        return aeroportResponse;
    }

    @Override
    public AeroportResponse getAeroportByName(String name) {

        Aeroport aeroport = aeroportRepository.findAeroportByName(name);
        if(aeroport==null)
            return null;

        AeroportResponse aeroportResponse = AeroportMapper.AeroportToAeroportResponse(aeroport);

        return aeroportResponse;
    }

    @Override
    public AeroportResponse updateAeroport(AeroportRequest aeroportRequest) {

        // we don't allow the user to change name
        Aeroport aeroport = aeroportRepository.findAeroportByName(aeroportRequest.name());
        if(aeroport==null)
            return null;

        Aeroport aeroport1 = Aeroport.builder()
                .idAeroport(aeroport.getIdAeroport())
                .delaiAttenteAuSol(aeroportRequest.delaiAttenteAuSol())
                .delaiAntiCollision(aeroportRequest.delaiAntiCollision())
                .dureeBoucleAttente(aeroportRequest.dureeBoucleAttente())
                .localisation(aeroportRequest.localisation())
                .name(aeroportRequest.name())
                .nombreDePlaceAuSol(aeroportRequest.nombreDePlaceAuSol())
                .nombrePistes(aeroportRequest.nombrePistes())
                .tempsAccessAuxPistes(aeroportRequest.tempsAccessAuxPistes())
                .tempsDecollageAtterrissage(aeroportRequest.tempsDecollageAtterrissage())
                .escale(aeroport.getEscale())
                .volDepart(aeroport.getVolDepart())
                .volArrivee(aeroport.getVolArrivee())
                .build();

        AeroportResponse aeroportResponse = AeroportMapper.AeroportToAeroportResponse(aeroport1);

        aeroportRepository.save(aeroport1);

        return aeroportResponse;
    }

    @Override
    public void removeAeroportByName(String name) {
        Aeroport aeroport = aeroportRepository.findAeroportByName(name);
        if(aeroport!=null)
            aeroportRepository.findAeroportByName(name);
    }
}
