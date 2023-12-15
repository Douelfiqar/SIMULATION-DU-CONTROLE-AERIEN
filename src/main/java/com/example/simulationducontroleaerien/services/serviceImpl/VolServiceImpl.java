package com.example.simulationducontroleaerien.services.serviceImpl;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.simulationducontroleaerien.DTOs.VolDtos.VolRequest;
import com.example.simulationducontroleaerien.DTOs.VolDtos.VolResponse;
import com.example.simulationducontroleaerien.entities.Aeroport;
import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.Escale;
import com.example.simulationducontroleaerien.entities.Vol;
import com.example.simulationducontroleaerien.mappers.avion.AvionMapper;
import com.example.simulationducontroleaerien.mappers.vol.VolMapper;
import com.example.simulationducontroleaerien.repositories.AeroportRepository;
import com.example.simulationducontroleaerien.repositories.VolRepository;
import com.example.simulationducontroleaerien.services.VolService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VolServiceImpl implements VolService {
    private VolRepository volRepository;
    private AeroportRepository aeroportRepository;
    @Override
    /*
     * les escales vont être ajouter lors de le faire (car on peut pas connaitre la date de l'escale exactement
     *  par exemple dans le cas si un vol est fait a l'aeroport A puis il va fair un escale dans l'aeroport B mais l'aeroport B
     *  est compléte alors on va appliquer le temps d'attente jusqua une avion est sorter pour que notre vol faire l'escale alors il s'agit d'un retard
     *  si le vol va fair un autre escale dans aeroport C, il va le faire avec un retard
     *  
     *  de plus i y a pleuseur escale pas un
     */
    public VolResponse addVol(VolRequest volRequest) {
        Aeroport aeroportDepart = aeroportRepository.findAeroportByName(volRequest.nameAeroportDepart());
        Aeroport aeroportArrive = aeroportRepository.findAeroportByName(volRequest.nameAeroportArrive());

        Vol vol = Vol.builder()
                .heurDepart(volRequest.heurDepart())
                .heurArriver(volRequest.heurArriver())
                .aeroportDepart(aeroportDepart)
                .aeroportArrivee(aeroportArrive)
                //.avion(AvionMapper.avionRequestToAvion(volRequest.avion()))
                .build();
        volRepository.save(vol);
        VolResponse volResponse = VolMapper.VolToVolResponse(vol);
        return volResponse;
    }

    @Override
    public VolResponse getVolById(int id) {
        Vol vol = volRepository.findById(id).orElseThrow();
        VolResponse volResponse = VolMapper.VolToVolResponse(vol);

        return volResponse;
    }

   /*
    * ce methode a duex cas
    * soit on va l'appler pour modifier le vol (bien sur avant de la date de départ)
    * ou
    * ajouter un escale
    */
    @Override
    public VolResponse updateVol(VolRequest volRequest, int id, Escale escale) {
        Vol vol = volRepository.findById(id).orElseThrow();
        Aeroport aeroportDepart = aeroportRepository.findAeroportByName(volRequest.nameAeroportDepart());
        Aeroport aeroportArrive = aeroportRepository.findAeroportByName(volRequest.nameAeroportArrive());

        // calcule de plus court chemain
        
        if( vol.getHeurDepart().after(Date.valueOf(LocalDate.now()))) {
        	vol = Vol.builder()
                    .aeroportArrivee(aeroportDepart)
                    .aeroportArrivee(aeroportArrive)
                    .heurArriver(volRequest.heurArriver())
                    .heurDepart(volRequest.heurDepart())
                    //.avion(AvionMapper.avionRequestToAvion(volRequest.avion()))
                    .build();
        }
        else
	        if(vol.getHeurArriver().after(Date.valueOf(LocalDate.now())) && escale != null)
	        	vol.getEscale().add(escale);

        VolResponse volResponse = VolMapper.VolToVolResponse(vol);
        return volResponse;
    }

    /*
     * on va pas le remove si il est déjà fait le départ et aussi si il arrive
     */
    @Override
    public void removeVolById(int id) throws IllegalAccessException {
    	Vol vol = volRepository.findById(id).orElseThrow();
        if(Date.valueOf(LocalDate.now()).before(vol.getHeurDepart())) {
            volRepository.deleteById(id);
        }
        else
        	throw new IllegalAccessException("vous pouvez pas supprimer un vol qui déjà fait départ!");
    }
}
