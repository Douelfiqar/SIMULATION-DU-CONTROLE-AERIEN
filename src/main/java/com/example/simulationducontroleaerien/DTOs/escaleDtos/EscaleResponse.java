package com.example.simulationducontroleaerien.DTOs.escaleDtos;

import com.example.simulationducontroleaerien.DTOs.VolDtos.VolResponse;
import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportResponse;

import java.util.Date;

public record EscaleResponse(int idEscale,
                             Date dateEscale,
                             AeroportResponse aeroportResponse,
                             VolResponse vol) {
}
