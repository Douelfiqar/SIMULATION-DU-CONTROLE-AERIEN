package com.example.simulationducontroleaerien.DTOs.VolDtos;

import java.util.Date;

public record VolRequest(Date heurDepart,
                         Date heurArriver,
                         String nameAeroportDepart,
                         String nameAeroportArrive) {
}
