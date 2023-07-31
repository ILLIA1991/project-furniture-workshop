package project.furnitureworkshop.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.furnitureworkshop.demo.controller.dto.WoodSpeciesDTO;
import project.furnitureworkshop.demo.converter.WoodSpeciesConverter;
import project.furnitureworkshop.demo.exception.FurnitureWorkshopNotFoundException;
import project.furnitureworkshop.demo.repository.SpringDataWoodSpeciesRepository;
import project.furnitureworkshop.demo.repository.model.Wood_Species;
import project.furnitureworkshop.demo.validator.WoodSpeciesValidator;

import java.util.Collection;
@Service
@Transactional(readOnly = true)
public class WoodSpeciesServiceImpl implements WoodSpeciesService{

    private final SpringDataWoodSpeciesRepository woodSpeciesRepository;

    private final WoodSpeciesConverter woodSpeciesConverter;

    private final WoodSpeciesValidator woodSpeciesValidator;



    public WoodSpeciesServiceImpl(SpringDataWoodSpeciesRepository woodSpeciesRepository, WoodSpeciesConverter woodSpeciesConverter, WoodSpeciesValidator woodSpeciesValidator) {
        this.woodSpeciesRepository = woodSpeciesRepository;
        this.woodSpeciesConverter = woodSpeciesConverter;
        this.woodSpeciesValidator = woodSpeciesValidator;
    }

    @Override
    public Collection<WoodSpeciesDTO> getAllWoodSpecies() {
        Collection<Wood_Species> all = woodSpeciesRepository.findAll();
        return woodSpeciesConverter.convertToDto(all);
    }

    @Override
    public WoodSpeciesDTO getById(Integer id) {
        Wood_Species woodSpecies = woodSpeciesRepository.findById(id).orElseThrow(() -> new FurnitureWorkshopNotFoundException("Wood not found:" + id));
        return woodSpeciesConverter.convertToDto(woodSpecies);
    }

    @Override
    @Transactional
    public Integer createWoosSpecies(WoodSpeciesDTO woodSpeciesToCreate) {
        woodSpeciesValidator.validateWoodSpecies(woodSpeciesToCreate);
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
    public WoodSpeciesDTO updateWoodSpecies(Integer id, WoodSpeciesDTO woodSpeciesToUpdate) {
        Wood_Species woodSpecies = woodSpeciesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Wood species not found:" + id));
        Wood_Species entityToUpdate = woodSpeciesConverter.convertToEntity(woodSpeciesToUpdate);
        entityToUpdate.setId(id);
        Wood_Species updateEntity = woodSpeciesRepository.save(entityToUpdate);
        return woodSpeciesConverter.convertToDto(updateEntity);
    }
}
