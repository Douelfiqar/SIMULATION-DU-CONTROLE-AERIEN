package com.example.simulationducontroleaerien.algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;
import com.example.simulationducontroleaerien.entities.Aeroport;
import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.Vol;

public class AeroportAlgorithm {
	private static final double EARTH_RADIUS = 6371;
	
	private Aeroport aeroport;

	public AeroportAlgorithm(Aeroport aeroport) {
		super();
		this.aeroport = aeroport;
	}

	public Aeroport getAeroport() {
		return aeroport;
	}

	public void setAeroport(Aeroport aeroport) {
		this.aeroport = aeroport;
	}
	
	
	// calcule de distance par la formule de Haversine a=sin^2 (2Δlat)+ cos(lat1)*cos(lat2)*sin^2 (2Δlon)
	// d = 2*R*arcsin(sqrt(a))
	private double calculeDistance(Aeroport aeroport1, Aeroport aeroport2) {
        double lat1 = Math.toRadians(aeroport1.getX());
        double lon1 = Math.toRadians(aeroport1.getY());
        double lat2 = Math.toRadians(aeroport2.getX());
        double lon2 = Math.toRadians(aeroport2.getY());

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(dlon / 2) * Math.sin(dlon / 2);
        
        double distance = 2 * EARTH_RADIUS * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return distance;
    }
	
	// calcule distanse aeroport au autre aeroport
	public void calculeDistanceAuxAutreAeroport(Collection<Aeroport> aeroports) {
	    Map<Aeroport, Double> distances = new HashMap<>();
	    for (Aeroport aeroport1 : aeroports) {
	        if (aeroport1!=null && !aeroport1.equals(aeroport)) {
	            Double distance = calculeDistance(aeroport, aeroport1);
	            Aeroport aeroport3 = Aeroport.builder()
	            		.delaiAntiCollision(aeroport1.getDelaiAntiCollision())
	            		.delaiAttenteAuSol(aeroport1.getDelaiAttenteAuSol())
	            		.dureeBoucleAttente(aeroport1.getDureeBoucleAttente())
//	            		.escale(aeroport1.getEscale())
	            		.idAeroport(aeroport1.getIdAeroport())
	            		.name(aeroport1.getName())
	            		.localisation(aeroport1.getLocalisation())
	            		.nombreDePlaceAuSol(aeroport1.getNombreDePlaceAuSol())
	            		.nombrePistes(aeroport1.getNombrePistes())
	            		.tempsAccessAuxPistes(aeroport1.getTempsAccessAuxPistes())
	            		.tempsDecollageAtterrissage(aeroport1.getTempsDecollageAtterrissage())
//	            		.volArrivee(aeroport1.getVolArrivee())
//	            		.volDepart(aeroport1.getVolDepart())
	            		.x(aeroport1.getX())
	            		.y(aeroport1.getY())
	            		.build();
	            distances.put(aeroport3, distance);
	            // The other aeroport should also add the distance of this aeroport
//	            aeroport1.getDistanceAuxAutresAeroports().put(aeroport, distance);
	        }
	    }

	    // Set the distances for the current aeroport
	    aeroport.getDistanceAuxAutresAeroports().putAll(distances);
	}


	public Avion getFreeAvionByType(Vol vol,TypeAvionDto type) {
		Collection<Vol> vols = aeroport.getVolArrivee();
		Collection<Avion> avions = new ArrayList<Avion>();
		
		// n'est pas pratique il faut ajouter les avion dans station pour ne pas ce trouver dens le probléme
		// de l'avion données deux fois dans le méme temps
		for (Vol vol1 : vols) {
			avions.add(vol1.getAvion());
		}
		
		for (Avion avion : avions) {
			AvionAlgorithm avionAlgo = new AvionAlgorithm(avion);
			if(avion.getTypeAvion().getName().equals(type.name()) && avionAlgo.isFree(vol)) {
				return avion;
			}
		}
		return null;
	}

	
	
	
}
