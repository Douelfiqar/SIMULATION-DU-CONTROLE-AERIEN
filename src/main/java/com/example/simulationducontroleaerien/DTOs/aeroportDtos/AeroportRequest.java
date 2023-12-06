package com.example.simulationducontroleaerien.DTOs.aeroportDtos;

public record AeroportRequest(String name,
                              String localisation,
                              int nombrePistes,
                              int nombreDePlaceAuSol,
                              int delaiAttenteAuSol,
                              int tempsAccessAuxPistes,
                              int delaiAntiCollision,
                              int tempsDecollageAtterrissage,
                              int dureeBoucleAttente) {
}
