package com.example.simulationducontroleaerien.DTOs.distanceAuxAutreAeroportDtos;

import lombok.Builder;

@Builder
public record DistaceAuxAutreAeroportResponse(
		String aeroportName,
		Double distance
		) {

}
