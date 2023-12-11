package com.example.simulationducontroleaerien.DTOs.aeroportDtos;

import com.example.simulationducontroleaerien.entities.Aeroport;
import com.example.simulationducontroleaerien.entities.Escale;
import com.example.simulationducontroleaerien.entities.Vol;
import lombok.Builder;

import java.util.Collection;
import java.util.Map;

@Builder
public record AeroportResponse(String name,
                               String localisation,
                               int nombrePistes,
                               int nombreDePlaceAuSol,
                               int delaiAttenteAuSol,
                               int tempsAccessAuxPist,
                               int delaiAntiCollision ,
                               int tempsDecollageAtterrissage,
                               int dureeBoucleAttente,
                               Map<Aeroport, Double> distanceAuxAutresAeroports,
                               Escale escale,
                               Collection<Vol> volDepart,
                               Collection<Vol> volArrivee,
                               Double x,
                               Double y) {
}
