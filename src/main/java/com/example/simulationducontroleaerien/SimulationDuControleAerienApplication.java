package com.example.simulationducontroleaerien;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionRequest;
import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;
import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportRequest;
import com.example.simulationducontroleaerien.algorithms.AeroportAlgorithm;
import com.example.simulationducontroleaerien.entities.Aeroport;
import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.Escale;
import com.example.simulationducontroleaerien.entities.TypeAvion;
import com.example.simulationducontroleaerien.repositories.AeroportRepository;
import com.example.simulationducontroleaerien.repositories.AvionRepository;
import com.example.simulationducontroleaerien.repositories.EscaleRepository;
import com.example.simulationducontroleaerien.repositories.TypeAvionRepository;
import com.example.simulationducontroleaerien.repositories.VolRepository;
import com.example.simulationducontroleaerien.services.AeroportService;
import com.example.simulationducontroleaerien.services.AvionService;
import com.example.simulationducontroleaerien.services.TypeAvionService;

@SpringBootApplication
public class SimulationDuControleAerienApplication {
	@Autowired
	AeroportRepository aeroportRepository;
	@Autowired
	EscaleRepository escaleRepository;
	@Autowired
	AvionRepository avionRepository;
	@Autowired
	TypeAvionRepository typeAvionRepository;
	@Autowired
	VolRepository volRepository;
    public static void main(String[] args) {
        SpringApplication.run(SimulationDuControleAerienApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner initializeData(AeroportService aeroportService,AvionService avionService,TypeAvionService typeAvionService) {
        return args -> {
        	
        	aeroportRepository.deleteAll();
            AeroportRequest a1 = new AeroportRequest("Los Angeles International Airport ",
            		"Los Angeles", 2, 300, 20, 15, 8, 40, 70, 33.942791, -118.410042);
            aeroportService.addAeroport(a1);
            AeroportRequest a2 = new AeroportRequest("Heathrow Airport (LHR), London, UK ",
            		"London, UK ", 2, 300, 25, 15, 8, 40, 70, 51.469401, -0.454364);
            aeroportService.addAeroport(a2);
            AeroportRequest a4 = new AeroportRequest("Tokyo Haneda Airport (HND), Japan",
            		null, 2, 300, 30, 15, 8, 40, 70, 35.553333, 139.781113);
            aeroportService.addAeroport(a4);
            AeroportRequest a5 = new AeroportRequest("Dubai International Airport (DXB), UAE",
            		"Japan", 2, 300, 15, 15, 8, 40, 70,25.2532,55.3657);
            aeroportService.addAeroport(a5);
            AeroportRequest a6 = new AeroportRequest("Sydney Kingsford Smith Airport (SYD), Australia",
            		"Australia", 2, 300, 22, 15, 8, 40, 70,-33.946111,151.177222);
            aeroportService.addAeroport(a6);
            AeroportRequest a7 = new AeroportRequest("Beijing Capital International Airport (PEK), China",
            		"China", 2, 300, 21, 15, 8, 40, 70,40.0799,116.6031);
            aeroportService.addAeroport(a7);
            AeroportRequest a8 = new AeroportRequest("Frankfurt Airport (FRA), Germany",
            		"Germany", 2, 300, 20, 15, 8, 40, 70,50.0336,8.5706);
            aeroportService.addAeroport(a8);
            AeroportRequest a9 = new AeroportRequest("Johannesburg OR Tambo International Airport (JNB), South Africa",
            		"South Africa", 2, 300, 27, 15, 8, 40, 70,-26.1361,28.2411);
            aeroportService.addAeroport(a9);
            AeroportRequest a10 = new AeroportRequest("Mexico City International Airport (MEX), Mexico",
            		"Mexico", 2, 300, 23, 15, 8, 40, 70,19.4362, -99.0721);
            aeroportService.addAeroport(a10);                  
            
            
            AeroportRequest a11 = new AeroportRequest("Casablanca Mohammed V International Airport (CMN), Morocco",
            		"Morocco", 2, 300, 30, 15, 8, 40, 70, 33.3675,-7.5899);
            aeroportService.addAeroport(a11);
            AeroportRequest a21 = new AeroportRequest("Cairo International Airport (CAI), Egypt",
            		"Egypt", 2, 300, 26, 15, 8, 40, 70, 30.1219, 31.4056);
            aeroportService.addAeroport(a21);
            AeroportRequest a211 = new AeroportRequest("Paris Charles de Gaulle Airport (CDG), France",
            		"France", 2, 300, 23, 15, 8, 40, 70,49.0097,2.5479);
            aeroportService.addAeroport(a211);
            AeroportRequest a2111 = new AeroportRequest("Toronto Pearson International Airport (YYZ), Canada",
            		"Canada", 2, 300, 27, 15, 8, 40, 70,43.6777,-79.6248);
            aeroportService.addAeroport(a2111);
            AeroportRequest b1 = new AeroportRequest("Dublin Airport (DUB), Ireland",
            		"Ireland", 2, 300, 20, 15, 8, 40, 70,53.4213, -6.2701);
            aeroportService.addAeroport(b1);
            AeroportRequest b3 = new AeroportRequest("Munich Airport (MUC), Germany",
            		"Germany", 2, 300, 20, 15, 8, 40, 70, 48.3537,11.7750);
            aeroportService.addAeroport(b3);
            AeroportRequest b4 = new AeroportRequest("Istanbul Airport (IST), Turkey",
            		"Turkey", 2, 300, 20, 15, 8, 40, 70,41.2756,28.7519);
            aeroportService.addAeroport(b4);
            AeroportRequest b5 = new AeroportRequest("Cape Town International Airport (CPT), South Africa",
            		"South Africa", 2, 300, 22, 15, 8, 40, 70,-33.9700,18.6022);
            aeroportService.addAeroport(b5);
            AeroportRequest b6 = new AeroportRequest("JBangkok Suvarnabhumi Airport (BKK), Thailand",
            		"Thailand", 2, 300, 25, 15, 8, 40, 70,13.6915,100.7501);
            aeroportService.addAeroport(b6);
            calculateDistance();
            
	            typeAvionService.addTypeAvion(TypeAvionDto.builder()
	            		.consomationBoucleAttente(20)
	            		.consomationNormale(30)
	            		.name("short")
	            		.vitesseBoucleAttente(100)
	            		.vitesseNormale(175)
	            		.build()
	            		);
	            avionService.addAvion(AvionRequest.builder()
	            		.name("avion")
	            		.nameTypeAvionDto("short")
	            		.build());
        };
    }

//    public CommandLineRunner initializeData(AeroportRepository aeroportRepository, AvionRepository avionRepository) {
//        return args -> {
//            Aeroport aeroportDepart = aeroportRepository.findAeroportByName("MOHAMMED V INTERNATIONAL AIRPORT - CASABLANCA AIRPORT");
//            Aeroport aeroportArivee = aeroportRepository.findAeroportByName("Los Angeles International Airport ");
//
//        };
//    }
    
    public Collection<Aeroport> getAeroports(){
        return aeroportRepository.findAll();
    }
    
    public void calculateDistance() {
    	Collection<Aeroport> aeroports = getAeroports();
    	for (Aeroport aeroport : aeroports) {
			new AeroportAlgorithm(aeroport).calculeDistanceAuxAutreAeroport(aeroports);
			aeroportRepository.save(aeroport);
		}
    	
     }
}
