package com.example.simulationducontroleaerien.DTOs.AvionDtos;

import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;
import lombok.Builder;

@Builder
public record AvionResponse(int id,
                            String numeroSerie,
                            String name,
                            TypeAvionDto typeAvionDto) {
}
