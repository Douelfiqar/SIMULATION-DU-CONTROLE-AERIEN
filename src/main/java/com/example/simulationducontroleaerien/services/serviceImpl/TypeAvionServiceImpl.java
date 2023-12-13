package com.example.simulationducontroleaerien.services.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.simulationducontroleaerien.DTOs.TypeAvionDtos.TypeAvionDto;
import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.TypeAvion;
import com.example.simulationducontroleaerien.mappers.typeAvion.TypeAvionMapper;
import com.example.simulationducontroleaerien.repositories.AvionRepository;
import com.example.simulationducontroleaerien.repositories.TypeAvionRepository;
import com.example.simulationducontroleaerien.services.AvionService;
import com.example.simulationducontroleaerien.services.TypeAvionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TypeAvionServiceImpl implements TypeAvionService {
    private TypeAvionRepository typeAvionRepository;
    private final AvionService avionService;
    @Override
    public TypeAvionDto addTypeAvion(TypeAvionDto typeAvionDto) {
        TypeAvion typeAvion = TypeAvion.builder()
                .vitesseNormale(typeAvionDto.vitesseNormale())
                .vitesseBoucleAttente(typeAvionDto.vitesseBoucleAttente())
                .consomationBoucleAttente(typeAvionDto.consomationBoucleAttente())
                .consomationNormale(typeAvionDto.consomationNormale())
                .build();

        typeAvionRepository.save(typeAvion);

        TypeAvionDto typeAvionDto2 = TypeAvionMapper.TypeAvionToTypeAvionDto(typeAvion);

        return typeAvionDto2;
    }

    @Override
    public TypeAvionDto getTypeAvionByName(String name) {
        TypeAvion typeAvion = typeAvionRepository.findTypeAvionByName(name);
        if(typeAvion == null)
            return null;

        TypeAvionDto typeAvionDto = TypeAvionMapper.TypeAvionToTypeAvionDto(typeAvion);
        return typeAvionDto;
    }

    @Override
    public TypeAvionDto updateTypeAvion(TypeAvionDto typeAvionDto) {
        TypeAvion typeAvion = typeAvionRepository.findTypeAvionByName(typeAvionDto.name());
        if(typeAvion == null)
            return null;

        TypeAvion typeAvion1 = TypeAvion.builder()
                .vitesseNormale(typeAvionDto.vitesseNormale())
                .consomationNormale(typeAvion.getConsomationNormale())
                .vitesseBoucleAttente(typeAvion.getVitesseBoucleAttente())
                .consomationBoucleAttente(typeAvionDto.consomationBoucleAttente())
                .build();
        // je panse que c'est un erreur la dérnier modification est typeAvion1 n'est pas typeAvion
        TypeAvionDto typeAvionDto1 = TypeAvionMapper.TypeAvionToTypeAvionDto(typeAvion1);
        return typeAvionDto1;
    }

    // on peut pas supprimer un type d'avion déjà utiliser (forentKey problème)
    @Override
    public void removeTypeAvionByName(String name) throws IllegalAccessException {
        TypeAvion typeAvion = typeAvionRepository.findTypeAvionByName(name);

        if (typeAvion != null) {
            List<Avion> avions = avionService.getAvionsByType(typeAvion);

            if (avions == null || avions.isEmpty()) {
                typeAvionRepository.removeTypeAvionByName(name);
            } else {
                throw new IllegalAccessException("Vous ne pouvez pas supprimer un type d'avion qui est déjà utilisé dans des avions. Supprimez d'abord les avions de ce type, puis supprimez le type.");
            }
        } else {
            throw new IllegalArgumentException("Type d'avion non trouvé avec le nom : " + name);
        }
    }
}
