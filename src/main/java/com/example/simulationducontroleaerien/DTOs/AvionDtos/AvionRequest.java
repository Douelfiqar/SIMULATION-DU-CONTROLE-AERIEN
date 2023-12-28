package com.example.simulationducontroleaerien.DTOs.AvionDtos;
import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;
import lombok.Builder;

@Builder
public record AvionRequest(String name,
                           String nameTypeAvionDto) {
}
