package com.example.simulationducontroleaerien.DTOs.escaleDtos;

import java.util.Date;

public record EscaleRequest(Date dateEscale, String nameAeroport, int idVol) {
}