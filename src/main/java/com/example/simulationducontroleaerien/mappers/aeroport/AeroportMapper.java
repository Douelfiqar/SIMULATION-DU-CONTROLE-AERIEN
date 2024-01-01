package com.example.simulationducontroleaerien.mappers.aeroport;

import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportRequest;
import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportResponse;
import com.example.simulationducontroleaerien.entities.Aeroport;
import com.example.simulationducontroleaerien.mappers.distanceAuxAutreAeroport.DistanceAuxAutreAeroportMapper;

public class AeroportMapper {
    public static Aeroport AeroportdtoToAeroport(AeroportRequest aeroportDto){

        Aeroport aeroport = Aeroport.builder()
                .delaiAntiCollision(aeroportDto.delaiAntiCollision())
                .name(aeroportDto.name())
                .dureeBoucleAttente(aeroportDto.dureeBoucleAttente())
                .localisation(aeroportDto.localisation())
                .tempsAccessAuxPistes(aeroportDto.tempsAccessAuxPistes())
                .nombrePistes(aeroportDto.nombrePistes())
                .tempsDecollageAtterrissage(aeroportDto.tempsDecollageAtterrissage())
                .nombreDePlaceAuSol(aeroportDto.nombreDePlaceAuSol())
                .x(aeroportDto.x())
                .y(aeroportDto.y())
                .build();
        return aeroport;
    }

    public static AeroportResponse AeroportToAeroportResponse(Aeroport aeroport){


        AeroportResponse aeroportResponse = AeroportResponse.builder()
                .delaiAttenteAuSol(aeroport.getDelaiAttenteAuSol())
                .escale(aeroport.getEscale())
                .volDepart(aeroport.getVolDepart())
                .volArrivee(aeroport.getVolArrivee())
                .distanceAuxAutresAeroports(DistanceAuxAutreAeroportMapper.distanceToDistanceResponce(aeroport.getDistanceAuxAutresAeroports()))
                .delaiAntiCollision(aeroport.getDelaiAntiCollision())
                .dureeBoucleAttente(aeroport.getDureeBoucleAttente())
                .localisation(aeroport.getLocalisation())
                .name(aeroport.getName())
                .nombreDePlaceAuSol(aeroport.getNombreDePlaceAuSol())
                .nombrePistes(aeroport.getNombrePistes())
                .tempsAccessAuxPist(aeroport.getTempsAccessAuxPistes())
                .tempsDecollageAtterrissage(aeroport.getTempsDecollageAtterrissage())
                .x(aeroport.getX())
                .y(aeroport.getY())
                .build();

        return aeroportResponse;
    }
}