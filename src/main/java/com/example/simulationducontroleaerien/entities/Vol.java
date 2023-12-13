package com.example.simulationducontroleaerien.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Vol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date heurDepart;
    private Date heurArriver;
    @OneToMany
    private List<Escale> escale;
    @ManyToOne
    private Aeroport aeroportDepart;
    @ManyToOne
    private Aeroport aeroportArrivee;
    @OneToMany
    private Avion avion;

	
	// application de l'algorithme de dijkstra pour trouver le plus court chemain pour l'earoport
	/*
	 * il faut donner le vol a question
	 * la méthode va returner liste des earoports qui vont être visiter par le vol
	 * remerque : ici je fait pas la condition de réservoire
	 */
	public List<Aeroport> volPath() {
		Aeroport depart = aeroportDepart;
		Aeroport arrive = aeroportArrivee;
        List<Aeroport> path = new ArrayList<>();
        Map<Aeroport,Double> distances = new HashMap<>();
        List<Aeroport> visited = new ArrayList<>();
        Double INFINITY = Double.MAX_VALUE;
        
        distances.put(depart, 0.0);
        path.add(depart);
        Set<Aeroport> earoports = depart.getDistanceAuxAutresAeroports().keySet();
        for (Aeroport aeroport : earoports) {
            distances.put(aeroport, INFINITY);
        }
        Aeroport minEaroport = depart;
       
        while(!arrive.equals(minEaroport)) {
        	visited.add(minEaroport);
            for(Aeroport earoport : distances.keySet()) {
            	if(distances.get(earoport) > distances.get(minEaroport) + minEaroport.getDistanceAuxAutresAeroports().get(earoport)) {
            		distances.put(earoport, distances.get(minEaroport) + minEaroport.getDistanceAuxAutresAeroports().get(earoport) );
            		if(earoport.equals(arrive)) {
            			path.add(minEaroport);
            		}
            	}
            }
            minEaroport = calculerMinDistance(minEaroport,visited);
        }

        return path;
    }
    
    private Aeroport calculerMinDistance(Aeroport currentEaroport, List<Aeroport> visited) {
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

	private boolean isVisited(Aeroport earoport, List<Aeroport> visited) {
		return visited.contains(earoport);
	}
}
