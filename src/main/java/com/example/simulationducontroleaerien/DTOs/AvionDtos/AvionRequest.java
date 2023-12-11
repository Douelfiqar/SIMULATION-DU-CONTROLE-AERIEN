package com.example.simulationducontroleaerien.DTOs.AvionDtos;
import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;

public record AvionRequest(String numeroSerie,
                           double vitesseNormale,
                           double vitesseBoucleAttente,
                           double consomationNormale,
                           double consomationBoucleAttente,
                           TypeAvionDto typeAvionDto) {
}
