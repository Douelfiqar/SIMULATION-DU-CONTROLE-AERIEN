package com.example.simulationducontroleaerien.DTOs.VolDtos;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionResponse;
import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportResponse;
import com.example.simulationducontroleaerien.DTOs.escaleDtos.EscaleResponse;
import lombok.Builder;

import java.util.Date;
@Builder
public record VolResponse(Date heurDepart,
                          Date heurArriver,
                          EscaleResponse escale,
                          AeroportResponse aeroportDepart,
                          AeroportResponse aeroportArrive,
                          AvionResponse avion) {
}
