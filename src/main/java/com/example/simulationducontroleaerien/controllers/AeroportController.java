package com.example.simulationducontroleaerien.controllers;

import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportRequest;
import com.example.simulationducontroleaerien.DTOs.aeroportDtos.AeroportResponse;
import com.example.simulationducontroleaerien.services.AeroportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aeroports")
@AllArgsConstructor
public class AeroportController {

    private final AeroportService aeroportService;

    @PostMapping
    public ResponseEntity<AeroportResponse> addAeroport(@RequestBody AeroportRequest aeroportRequest) {
        AeroportResponse aeroportResponse = aeroportService.addAeroport(aeroportRequest);
        return new ResponseEntity<>(aeroportResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AeroportResponse>> listAeroport(){
        return new ResponseEntity<>(aeroportService.lisAeroport(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<AeroportResponse> getAeroportByName(@PathVariable String name) {
        AeroportResponse aeroportResponse = aeroportService.getAeroportByName(name);
        if (aeroportResponse != null) {
            return new ResponseEntity<>(aeroportResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<AeroportResponse> updateAeroport(
            @PathVariable String name,
            @RequestBody AeroportRequest aeroportRequest) {
        AeroportResponse aeroportResponse = aeroportService.updateAeroport(aeroportRequest);
        if (aeroportResponse != null) {
            return new ResponseEntity<>(aeroportResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> removeAeroportByName(@PathVariable String name) {
        aeroportService.removeAeroportByName(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
