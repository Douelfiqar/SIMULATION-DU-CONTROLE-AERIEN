package com.example.simulationducontroleaerien.mappers.distanceAuxAutreAeroport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.example.simulationducontroleaerien.DTOs.distanceAuxAutreAeroportDtos.DistaceAuxAutreAeroportResponse;
import com.example.simulationducontroleaerien.entities.Aeroport;
import com.example.simulationducontroleaerien.mappers.aeroport.AeroportMapper;

public class DistanceAuxAutreAeroportMapper {

	public static Collection<DistaceAuxAutreAeroportResponse> distanceToDistanceResponce(Map<Aeroport,Double> distance) {
		Collection<DistaceAuxAutreAeroportResponse> distances = new ArrayList<DistaceAuxAutreAeroportResponse>();
		for (Map.Entry<Aeroport, Double> entry : distance.entrySet()) {
			Aeroport aeroport = entry.getKey();
			Double dis = entry.getValue();
			
			distances.add(DistaceAuxAutreAeroportResponse.builder()
					.aeroportName(aeroport.getName())
					.distance(dis)
					.build()
					);
		}
		return distances;
	}
}
