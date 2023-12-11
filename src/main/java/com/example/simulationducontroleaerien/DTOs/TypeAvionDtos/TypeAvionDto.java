package com.example.simulationducontroleaerien.DTOs.TypeAvionDtos;

import lombok.Builder;

@Builder
public record TypeAvionDto (String name,
                           double vitesseNormale,
                           double vitesseBoucleAttente,
                           double consomationNormale,
                           double consomationBoucleAttente){
}
