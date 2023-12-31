package com.example.simulationducontroleaerien.controllers;

import com.example.simulationducontroleaerien.DTOs.VolDtos.VolRequest;
import com.example.simulationducontroleaerien.DTOs.VolDtos.VolResponse;
import com.example.simulationducontroleaerien.DTOs.escaleDtos.EscaleRequest;
import com.example.simulationducontroleaerien.entities.Escale;
import com.example.simulationducontroleaerien.services.VolService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vols")
@AllArgsConstructor
public class VolController {

    private final VolService volService;


    @GetMapping("/getAllVol")
    public ResponseEntity<List<VolResponse>> listResponseEntity(){
        return new ResponseEntity<>(volService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VolResponse> addVol(
            @RequestBody VolRequest volRequest) {

        VolResponse volResponse = volService.addVol(volRequest);

        return new ResponseEntity<>(volResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolResponse> getVolById(@PathVariable int id) {
        VolResponse volResponse = volService.getVolById(id);
        if (volResponse != null) {
            return new ResponseEntity<>(volResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VolResponse> updateVol(
            @PathVariable int id,
            @RequestBody VolRequest volRequest,
            @RequestBody(required = false) Escale escale) {
        VolResponse updatedVolResponse = volService.updateVol(volRequest, id, escale);
        if (updatedVolResponse != null) {
            return new ResponseEntity<>(updatedVolResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeVolById(@PathVariable int id) {
        volService.removeVolById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
