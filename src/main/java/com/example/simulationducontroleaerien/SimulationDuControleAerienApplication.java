package com.example.simulationducontroleaerien;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionRequest;
import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionResponse;
import com.example.simulationducontroleaerien.DTOs.VolDtos.VolRequest;
import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportRequest;
import com.example.simulationducontroleaerien.entities.Aeroport;
import com.example.simulationducontroleaerien.entities.Vol;
import com.example.simulationducontroleaerien.repositories.AeroportRepository;
import com.example.simulationducontroleaerien.repositories.AvionRepository;
import com.example.simulationducontroleaerien.services.AeroportService;
import com.example.simulationducontroleaerien.services.AvionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SimulationDuControleAerienApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimulationDuControleAerienApplication.class, args);
    }

    public CommandLineRunner initializeData(AeroportRepository aeroportRepository, AvionRepository avionRepository) {
        return args -> {
            Aeroport aeroportDepart = aeroportRepository.findAeroportByName("MOHAMMED V INTERNATIONAL AIRPORT - CASABLANCA AIRPORT");
            Aeroport aeroportArivee = aeroportRepository.findAeroportByName("Los Angeles International Airport ");

        };
    }
}
