package com.example.simulationducontroleaerien.controllers;

import com.example.simulationducontroleaerien.DTOs.escaleDtos.EscaleRequest;
import com.example.simulationducontroleaerien.DTOs.escaleDtos.EscaleResponse;
import com.example.simulationducontroleaerien.services.EscaleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/escales")
@AllArgsConstructor
public class EscaleController {

    private final EscaleService escaleService;

    @GetMapping("getAllEscales")
    public ResponseEntity<List<EscaleResponse>> geListEscale(){
        return new ResponseEntity<>(escaleService.lisEscale() ,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EscaleResponse> addEscale(@RequestBody EscaleRequest escaleRequest) {
        EscaleResponse escaleResponse = escaleService.addEscale(escaleRequest);
        return new ResponseEntity<>(escaleResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscaleResponse> getEscaleById(@PathVariable int id) {
        EscaleResponse escaleResponse = escaleService.getEscaleById(id);
        if (escaleResponse != null) {
            return new ResponseEntity<>(escaleResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscaleResponse> updateEscale(
            @PathVariable int id,
            @RequestBody EscaleRequest escaleRequest) {
        EscaleResponse updatedEscaleResponse = escaleService.updateEscale(escaleRequest, id);
        if (updatedEscaleResponse != null) {
            return new ResponseEntity<>(updatedEscaleResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeEscaleById(@PathVariable int id) {
        escaleService.removeEscaleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
