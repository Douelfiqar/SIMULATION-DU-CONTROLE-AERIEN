package com.example.simulationducontroleaerien.services.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.simulationducontroleaerien.DTOs.VolDtos.VolRequest;
import com.example.simulationducontroleaerien.DTOs.VolDtos.VolResponse;
import com.example.simulationducontroleaerien.algorithms.VolAlgorithm;
import com.example.simulationducontroleaerien.entities.Aeroport;
import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.Escale;
import com.example.simulationducontroleaerien.entities.Vol;
import com.example.simulationducontroleaerien.mappers.aeroport.AeroportMapper;
import com.example.simulationducontroleaerien.mappers.avion.AvionMapper;
import com.example.simulationducontroleaerien.mappers.escale.EscaleMapper;
import com.example.simulationducontroleaerien.mappers.vol.VolMapper;
import com.example.simulationducontroleaerien.repositories.AeroportRepository;
import com.example.simulationducontroleaerien.repositories.AvionRepository;
import com.example.simulationducontroleaerien.repositories.EscaleRepository;
import com.example.simulationducontroleaerien.repositories.VolRepository;
import com.example.simulationducontroleaerien.services.VolService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class VolServiceImpl implements VolService {
    private VolRepository volRepository;
    private AeroportRepository aeroportRepository;
    private AvionRepository avionRepository;
    private EscaleRepository escaleRepository;
    private VolMapper volMapper;
    private EscaleMapper escaleMapper;
    @Override
    public List<VolResponse> findAll() {
        List<Vol> volList = volRepository.findAll();
        List<VolResponse> volResponses = volList.stream()
                .map(vol -> volMapper.VolToVolResponse(vol))
                .collect(Collectors.toList());
        return volResponses;
    }

    @Override
    public VolResponse addVol(VolRequest volRequest) {

        Aeroport aeroportDepart = aeroportRepository.findAeroportByName(volRequest.nameAeroportDepart());
        Aeroport aeroportArrive = aeroportRepository.findAeroportByName(volRequest.nameAeroportArrive());
        Avion avion = avionRepository.findAvionByNumeroSerie(volRequest.numeroSerieAvion());

        // ADD STATIC HOUR FOR TEST
        Date currentDate = new Date();

        // Add 2 hours (2 * 60 * 60 * 1000 milliseconds)
        long twoHoursInMilliseconds = 2 * 60 * 60 * 1000;
        long updatedTimeMillis = currentDate.getTime() + twoHoursInMilliseconds;

        // Create a new Date object with the updated time
        Date updatedDate = new Date(updatedTimeMillis);

        // i need List of Escale
        

       Vol vol = Vol.builder()
                .heurDepart(volRequest.heurDepart())
                .heurArriver(updatedDate)
                .aeroportDepart(aeroportDepart)
                .aeroportArrivee(aeroportArrive)
                .avion(avion)
                .build();
       
       avion.getVol().add(vol);
       
       //add List of Escale
		List<Aeroport> path = VolAlgorithm.volPath(vol);
		System.out.println("path size"+ path.size());
		List<Escale> escales = new ArrayList<>();
		for (Aeroport aeroport : path) {
			System.out.println(aeroport.getIdAeroport());
		}
		Escale lastEscale = null;
		double vitess = vol.getAvion().getTypeAvion().getVitesseNormale();
		for (Aeroport aeroport : path) {
			if(!(aeroport.equals(vol.getAeroportDepart()) || aeroport.equals(vol.getAeroportArrivee()))) {
				Escale escale = Escale.builder()
						.aeroport(aeroport)
						.vol(vol)
						.build();
//				System.out.println(escale.toString());
				Date escaleDate = null;
				if(lastEscale != null) {
					// calculate the time of the escale
					try {
						// time en h
						Double time = aeroport.getDistanceAuxAutresAeroports().get(lastEscale.getAeroport()) / vitess;
						
						long escaleTimeInMillis = lastEscale.getDateEscale().getTime() + (long) (time * 60 * 60 * 1000); 
		                 escaleDate = new Date(escaleTimeInMillis);
					}catch (Exception e) {
						System.out.println("the value of the speed is 0!");
					}
				}else {
					try {
					Double time = aeroport.getDistanceAuxAutresAeroports().get(vol.getAeroportDepart()) / vitess;
					
					long escaleTimeInMillis = vol.getHeurDepart().getTime() + (long) (time * 60 * 60 * 1000); 
	                escaleDate = new Date(escaleTimeInMillis);
	                
					}catch (Exception e) {
						System.out.println("the value of the speed is 0!");
					}
				}
				escale.setDateEscale(escaleDate);
				System.out.println("date of the escale is : " + escale.getDateEscale());
				lastEscale = escale;
				escales.add(escale);
			}
		}
		Date arriveDate = null;
		if(!escales.isEmpty()) {
			try {
				Escale escaleFinal = escales.get(escales.size() - 1);
				Double time = escaleFinal.getAeroport().getDistanceAuxAutresAeroports().get(vol.getAeroportArrivee()) / vitess;
				
				long escaleTimeInMillis = escaleFinal.getDateEscale().getTime() + (long) (time * 60 * 60 * 1000); 
				arriveDate = new Date(escaleTimeInMillis);
                vol.setHeurArriver(arriveDate);
				}catch (Exception e) {
					System.out.println("the value of the speed is 0!");
				}
		}else {
			try {
			Double time = vol.getAeroportDepart().getDistanceAuxAutresAeroports().get(vol.getAeroportArrivee()) / vitess;
			
			long escaleTimeInMillis = vol.getHeurDepart().getTime() + (long) (time * 60 * 60 * 1000); 
			arriveDate = new Date(escaleTimeInMillis);
			
            vol.setHeurArriver(arriveDate);
			}catch (Exception e) {
				System.out.println("the value of the speed is 0!");
			}
		}
		
		// add escales to the vol
		vol.setEscale(escales);
		
		
		
		
/*
        if(escaleRequest != null){
            List<Escale> escaleList = new ArrayList<>();
            Escale escale = escaleMapper.escaleRequestToEscale(escaleRequest);
            escaleList.add(escale);
            vol.setEscale(escaleList);
            escaleRepository.save(escale);
        }*/

        vol = volRepository.save(vol);
        for (Escale escale : escales) {
			System.out.println("escale has seved!");
			escaleRepository.save(escale);
		}
        VolResponse volResponse = volMapper.VolToVolResponse(vol);
       
       return volResponse;
    }

    @Override
    public VolResponse getVolById(int id) {
        Vol vol = volRepository.findById(id).orElseThrow();
        VolResponse volResponse = volMapper.VolToVolResponse(vol);

        return volResponse;
    }

    @Override
    public VolResponse updateVol(VolRequest volRequest, int id, Escale escale) {
        Vol vol = volRepository.findById(id).orElseThrow();
        Aeroport aeroportDepart = aeroportRepository.findAeroportByName(volRequest.nameAeroportDepart());
        Aeroport aeroportArrive = aeroportRepository.findAeroportByName(volRequest.nameAeroportArrive());
        Avion avion = avionRepository.findAvionByNumeroSerie(volRequest.numeroSerieAvion());

        Vol vol1 = Vol.builder()
                .aeroportArrivee(aeroportDepart)
                .aeroportArrivee(aeroportArrive)
                .heurDepart(volRequest.heurDepart())
                .avion(avion)
                .build();

        if(escale != null)
        {
            Collection<Escale> escaleCollection = vol1.getEscale();
            escaleCollection.add(escale);
            vol1.setEscale(escaleCollection);
        }

        VolResponse volResponse = volMapper.VolToVolResponse(vol1);
        return volResponse;
    }

    @Override
    public void removeVolById(int id) {
        Vol vol = volRepository.findById(id).orElseThrow();
        if (vol != null) {
        	vol.getAvion().getVol().remove(vol);
        	volRepository.delete(vol);
		}
        
    }

    // find the shortest path for a vol
	@Override
	public List<Aeroport> getShortestPath(int id) {
		Vol vol = volRepository.findById(id).orElseThrow();
		List<Aeroport> path = VolAlgorithm.volPath(vol);
		return path;
	}
}
