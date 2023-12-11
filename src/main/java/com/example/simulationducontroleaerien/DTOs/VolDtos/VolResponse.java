package com.example.simulationducontroleaerien.DTOs.VolDtos;

import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportResponse;
import com.example.simulationducontroleaerien.DTOs.escaleDtos.EscaleResponse;

import java.util.Date;

public record VolResponse(Date heurDepart,
                          Date heurArriver,
                          EscaleResponse escale,
                          AeroportResponse AeroportDepart,
                          AeroportResponse nameAeroportArrive) {
}
