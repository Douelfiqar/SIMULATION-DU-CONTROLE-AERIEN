package com.example.simulationducontroleaerien.DTOs.VolDtos;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionResponse;
import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportResponse;
import com.example.simulationducontroleaerien.DTOs.escaleDtos.EscaleResponse;
import com.example.simulationducontroleaerien.entities.Avion;
import lombok.Builder;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Builder
public record VolResponse(Date heurDepart,
                          Date heurArriver,
                          Collection<EscaleResponse> escale,
                          AeroportResponse aeroportDepart,
                          AeroportResponse aeroportArrive,
                          AvionResponse avionResponse) {
}
