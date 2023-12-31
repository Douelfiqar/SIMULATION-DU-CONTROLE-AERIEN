package com.example.simulationducontroleaerien.controllers;

import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;
import com.example.simulationducontroleaerien.exceptions.NameTypeAvionExist;
import com.example.simulationducontroleaerien.services.TypeAvionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typeavions")
@AllArgsConstructor
public class TypeAvionController {

    private final TypeAvionService typeAvionService;

    @GetMapping("getAllTypeAvion")
    public ResponseEntity<List<TypeAvionDto>> listTypeAvion(){
        return new ResponseEntity<>(typeAvionService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<TypeAvionDto> addTypeAvion(@RequestBody TypeAvionDto typeAvionDto) throws NameTypeAvionExist {
        TypeAvionDto resultDto = typeAvionService.addTypeAvion(typeAvionDto);
        return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<TypeAvionDto> getTypeAvionByName(@PathVariable String name) {
        TypeAvionDto typeAvionDto = typeAvionService.getTypeAvionByName(name);
        if (typeAvionDto != null) {
            return new ResponseEntity<>(typeAvionDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<TypeAvionDto> updateTypeAvion(
            @PathVariable String name,
            @RequestBody TypeAvionDto typeAvionDto) {
        TypeAvionDto updatedTypeAvionDto = typeAvionService.updateTypeAvion(typeAvionDto);
        if (updatedTypeAvionDto != null) {
            return new ResponseEntity<>(updatedTypeAvionDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> removeTypeAvionByName(@PathVariable String name) {
        typeAvionService.removeTypeAvionByName(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
