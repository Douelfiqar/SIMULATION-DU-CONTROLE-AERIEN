package com.example.simulationducontroleaerien.DTOs.VolDtos;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionRequest;
import com.example.simulationducontroleaerien.entities.Avion;
import lombok.Builder;

import java.util.Date;
@Builder
public record VolRequest(Date heurDepart,
                         String nameAeroportDepart,
                         String nameAeroportArrive,
                         String numeroSerieAvion) {
}
