package com.example.simulationducontroleaerien.DTOs.VolDtos;

import java.util.Date;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionRequest;

public record VolRequest(Date heurDepart,
                         Date heurArriver,
                         String nameAeroportDepart,
                         String nameAeroportArrive,
                         AvionRequest avion) {
}
