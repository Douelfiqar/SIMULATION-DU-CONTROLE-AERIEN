package com.example.simulationducontroleaerien.controllers;

import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionRequest;
import com.example.simulationducontroleaerien.DTOs.AvionDtos.AvionResponse;
import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.exceptions.TypeAvionNotExist;
import com.example.simulationducontroleaerien.services.AvionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avions")
@AllArgsConstructor
public class AvionController {

    private final AvionService avionService;

    @GetMapping("/getAllAvion")
    public ResponseEntity<List<AvionResponse>> getAllAvion(){
        return new ResponseEntity<>(avionService.listAvions(), HttpStatus.OK );
    }
    @PostMapping
    public ResponseEntity<AvionResponse> addAvion(@RequestBody AvionRequest avionRequest) throws TypeAvionNotExist {
        AvionResponse avionResponse = avionService.addAvion(avionRequest);
        return new ResponseEntity<>(avionResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{numeroSerie}")
    public ResponseEntity<AvionResponse> getAvionByNumeroSerie(@PathVariable String numeroSerie) {
        AvionResponse avionResponse = avionService.getAvionByNumeroSerie(numeroSerie);
        if (avionResponse != null) {
            return new ResponseEntity<>(avionResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{numeroSerie}")
    public ResponseEntity<AvionResponse> updateAvion(
            @PathVariable String numeroSerie,
            @RequestBody AvionRequest avionRequest) {
        AvionResponse avionResponse = avionService.updateAvion(avionRequest, numeroSerie);
        if (avionResponse != null) {
            return new ResponseEntity<>(avionResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{numeroSerie}")
    public ResponseEntity<Void> removeAvionByNumeroSerie(@PathVariable String numeroSerie) {
        avionService.removeAvionByName(numeroSerie);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
