package com.example.simulationducontroleaerien.DTOs.VolDtos;

import java.util.Date;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionRequest;
import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;

public record VolRequest(Date heurDepart,
                         Date heurArriver,
                         String nameAeroportDepart,
                         String nameAeroportArrive,
                         TypeAvionDto typeAvionDto) {
}
