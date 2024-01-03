package com.example.simulationducontroleaerien.algorithms;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;
import com.example.simulationducontroleaerien.entities.Aeroport;
import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.Vol;
import com.example.simulationducontroleaerien.repositories.AeroportRepository;

public class VolAlgorithm {
	@Autowired
	private static AeroportRepository aeroportRepository; 
	private Vol vol;

	public VolAlgorithm(Vol vol) {
		super();
		this.vol = vol;
	}

	public Vol getVol() {
		return vol;
	}

	public void setVol(Vol vol) {
		this.vol = vol;
	}
	
		public static List<Aeroport> volPath(Vol vol) {
			Aeroport depart = vol.getAeroportDepart();
			Aeroport arrive = vol.getAeroportArrivee();
	        List<Aeroport> path = new ArrayList<>();
	        Aeroport predecesseur = null;
	        Map<Aeroport,Double> distances = new LinkedHashMap<>();
	        List<Aeroport> visited = new ArrayList<>();
	        Double distanceTravirse = 0.0;
	        
	        
	        Aeroport minEaroport = depart;
	        if(estDistancePossible(vol,minEaroport.getDistanceAuxAutresAeroports().get(arrive))) {
	        	distances.put(minEaroport, minEaroport.getDistanceAuxAutresAeroports().get(arrive));
	        }else {
	        	distances.put(minEaroport, Double.MAX_VALUE);
	        }
	        visited.add(minEaroport);
	        
	        while(!arrive.equals(minEaroport)) {
	        	predecesseur = minEaroport;
	        	minEaroport = calculerMinDistance(minEaroport, visited);
	        	visited.add(minEaroport);
	        	distanceTravirse += predecesseur.getDistanceAuxAutresAeroports().get(minEaroport);
	        	if(!minEaroport.equals(arrive) && distances.get(predecesseur) > distanceTravirse + minEaroport.getDistanceAuxAutresAeroports().get(arrive)) {
	        		distances.put(minEaroport,distanceTravirse + minEaroport.getDistanceAuxAutresAeroports().get(arrive));
	        	}else {
	        		distances.put(minEaroport, distances.get(predecesseur));
	        	}
	        }
	        
	        Double dis = Double.MAX_VALUE;
	        for (Map.Entry<Aeroport, Double> entry : distances.entrySet()) {
				if(entry.getValue() < dis) { // >
					path.add(entry.getKey());
					dis = entry.getValue();
				}else {
					boolean estDansPos = false;
					boolean estChange = false;
					for (Map.Entry<Aeroport, Double> entry1 : distances.entrySet()) {
						if(entry1.getKey().equals(entry.getKey())) {
							estDansPos = true;
						}
						if(estDansPos && entry1.getValue() < entry.getValue() ) {
							
							path.add(entry.getKey());
							estChange = true;
						}
					}
					if(!estChange) {
						break;
					}
				}
			}
	        
	        path.add(arrive);
	        return path;
	    }
	
		public static boolean estDistancePossible(Vol vol, Double distance) {
			Double consommation = vol.getAvion().getTypeAvion().getConsomationNormale() * distance;
			Double longCourie = 14000.0;
			Double moyenneCourie = 6000.0;
			Double petiteCourie = 2000.0;
			String typeAvion = vol.getAvion().getTypeAvion().getName();
			switch (typeAvion) {
			case "petit" : 
				if(consommation <= petiteCourie)
					return true;
			case "long" : 
				if(consommation <= longCourie)
					return true;
			case "moyenne" :
				if(consommation <= moyenneCourie)
					return true;
			}
			return false;
			
		}
	    
	    private static Aeroport calculerMinDistance(Aeroport currentEaroport, List<Aeroport> visited) {
	    	Aeroport earoportMin = null;
	    	Map<Aeroport,Double> distances = currentEaroport.getDistanceAuxAutresAeroports();
	    	
	    	for (Aeroport earoport : distances.keySet()) {
				if(!isVisited(earoport,visited)) {
					if(earoportMin == null) {
						earoportMin = earoport;
					}else {
						if(distances.get(earoportMin) > distances.get(earoport)) {
							earoportMin = earoport;
						}
					}
				}
			}
			return earoportMin;
		}

		private static boolean isVisited(Aeroport earoport, List<Aeroport> visited) {
			return visited.contains(earoport);
		}
		
		public boolean reserverAvion(TypeAvionDto type) {
			AeroportAlgorithm aroportAlgo = new AeroportAlgorithm(vol.getAeroportDepart());
			Avion avion = aroportAlgo.getFreeAvionByType(vol,type);
			if(avion != null)
			{
				vol.setAvion(avion);
				return true;
			}
			return false;
		}
}
