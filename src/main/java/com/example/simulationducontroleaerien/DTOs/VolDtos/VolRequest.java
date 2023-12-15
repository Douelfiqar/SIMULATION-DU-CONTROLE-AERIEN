package com.example.simulationducontroleaerien.DTOs.VolDtos;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionRequest;
import com.example.simulationducontroleaerien.entities.Avion;

import java.util.Date;

public record VolRequest(Date heurDepart,
                         Date heurArriver,
                         String nameAeroportDepart,
                         String nameAeroportArrive,
                         AvionRequest avionRequest) {
}
