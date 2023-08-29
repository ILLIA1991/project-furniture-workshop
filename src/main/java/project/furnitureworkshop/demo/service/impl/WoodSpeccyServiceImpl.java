package project.furnitureworkshop.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.furnitureworkshop.demo.controller.dto.WoodSpeccyDTO;
import project.furnitureworkshop.demo.converter.WoodSpeccyConverter;
import project.furnitureworkshop.demo.exception.FurnitureWorkshopNotFoundException;
import project.furnitureworkshop.demo.repository.WoodSpeccyRepository;
import project.furnitureworkshop.demo.repository.model.WoodSpeccy;
import project.furnitureworkshop.demo.service.WoodSpeccyService;
import project.furnitureworkshop.demo.validator.WoodSpeccyValidator;

import java.util.Collection;

@Service
@Transactional(readOnly = true)
public class WoodSpeccyServiceImpl implements WoodSpeccyService {

    private final WoodSpeccyRepository woodSpeciesRepository;

    private final WoodSpeccyConverter woodSpeccyConverter;

    private final WoodSpeccyValidator woodSpeccyValidator;


    public WoodSpeccyServiceImpl(WoodSpeccyRepository woodSpeciesRepository, WoodSpeccyConverter woodSpeccyConverter, WoodSpeccyValidator woodSpeccyValidator) {
        this.woodSpeciesRepository = woodSpeciesRepository;
        this.woodSpeccyConverter = woodSpeccyConverter;
        this.woodSpeccyValidator = woodSpeccyValidator;
    }

    @Override
    public Collection<WoodSpeccyDTO> getAllWoodSpecies() {
        Collection<WoodSpeccy> all = woodSpeciesRepository.findAll();
        return woodSpeccyConverter.convertToDto(all);
    }

    @Override
    public WoodSpeccyDTO getById(Integer id) {
        WoodSpeccy woodSpecies = woodSpeciesRepository.findById(id).orElseThrow(() -> new FurnitureWorkshopNotFoundException("Wood not found: " + id));
        return woodSpeccyConverter.convertToDto(woodSpecies);
    }

    @Override
    @Transactional
    public Integer createWoodSpeccy(WoodSpeccyDTO woodSpeciesToCreate) {
        woodSpeccyValidator.validateWoodSpeccy(woodSpeciesToCreate);
        WoodSpeccy woodSpecies = woodSpeccyConverter.convertToEntity(woodSpeciesToCreate);
        WoodSpeccy saveWoodSpecies = woodSpeciesRepository.save(woodSpecies);
        return saveWoodSpecies.getId();
    }

    @Override
    @Transactional
    public void deleteWoodSpeccy(Integer id) {
        WoodSpeccy woodSpecies = woodSpeciesRepository.findById(id).orElseThrow(() -> new FurnitureWorkshopNotFoundException("Wood species not found: " + id));
        woodSpeciesRepository.delete(woodSpecies);

    }

    @Override
    @Transactional
    public WoodSpeccyDTO updateWoodSpeccy(Integer id, WoodSpeccyDTO woodSpeciesToUpdate) {
        woodSpeccyValidator.validateWoodSpeccy(woodSpeciesToUpdate);
        WoodSpeccy woodSpecies = woodSpeciesRepository.findById(id).orElseThrow(() -> new FurnitureWorkshopNotFoundException("Wood species not found: " + id));
        WoodSpeccy entityToUpdate = woodSpeccyConverter.convertToEntity(woodSpeciesToUpdate);
        entityToUpdate.setId(id);
        WoodSpeccy updateEntity = woodSpeciesRepository.save(entityToUpdate);
        return woodSpeccyConverter.convertToDto(updateEntity);
    }
}
