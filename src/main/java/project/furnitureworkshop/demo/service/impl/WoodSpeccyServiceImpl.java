package project.furnitureworkshop.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.furnitureworkshop.demo.controller.dto.WoodSpeccyDTO;
import project.furnitureworkshop.demo.converter.WoodSpeciesConverter;
import project.furnitureworkshop.demo.exception.FurnitureWorkshopNotFoundException;
import project.furnitureworkshop.demo.repository.WoodSpeccyRepository;
import project.furnitureworkshop.demo.repository.model.Wood_Species;
import project.furnitureworkshop.demo.service.WoodSpeccyService;
import project.furnitureworkshop.demo.validator.WoodSpeccyValidator;

import java.util.Collection;
@Service
@Transactional(readOnly = true)
public class WoodSpeccyServiceImpl implements WoodSpeccyService {

    private final WoodSpeccyRepository woodSpeciesRepository;

    private final WoodSpeciesConverter woodSpeciesConverter;

    private final WoodSpeccyValidator woodSpeccyValidator;



    public WoodSpeccyServiceImpl(WoodSpeccyRepository woodSpeciesRepository, WoodSpeciesConverter woodSpeciesConverter, WoodSpeccyValidator woodSpeccyValidator) {
        this.woodSpeciesRepository = woodSpeciesRepository;
        this.woodSpeciesConverter = woodSpeciesConverter;
        this.woodSpeccyValidator = woodSpeccyValidator;
    }

    @Override
    public Collection<WoodSpeccyDTO> getAllWoodSpecies() {
        Collection<Wood_Species> all = woodSpeciesRepository.findAll();
        return woodSpeciesConverter.convertToDto(all);
    }

    @Override
    public WoodSpeccyDTO getById(Integer id) {
        Wood_Species woodSpecies = woodSpeciesRepository.findById(id).orElseThrow(() -> new FurnitureWorkshopNotFoundException("Wood not found:" + id));
        return woodSpeciesConverter.convertToDto(woodSpecies);
    }

    @Override
    @Transactional
    public Integer createWoosSpecies(WoodSpeccyDTO woodSpeciesToCreate) {
        woodSpeccyValidator.validateWoodSpecies(woodSpeciesToCreate);
        Wood_Species woodSpecies = woodSpeciesConverter.convertToEntity(woodSpeciesToCreate);
        Wood_Species saveWoodSpecies =  woodSpeciesRepository.save(woodSpecies);
        return saveWoodSpecies.getId();
    }

    @Override
    @Transactional
    public void deleteWoodSpecies(Integer id) {
        Wood_Species woodSpecies = woodSpeciesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Wood species not found:" + id));
        woodSpeciesRepository.delete(woodSpecies);

    }

    @Override
    @Transactional
    public WoodSpeccyDTO updateWoodSpeccy(Integer id, WoodSpeccyDTO woodSpeciesToUpdate) {
        Wood_Species woodSpecies = woodSpeciesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Wood species not found:" + id));
        Wood_Species entityToUpdate = woodSpeciesConverter.convertToEntity(woodSpeciesToUpdate);
        entityToUpdate.setId(id);
        Wood_Species updateEntity = woodSpeciesRepository.save(entityToUpdate);
        return woodSpeciesConverter.convertToDto(updateEntity);
    }
}
