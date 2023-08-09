package project.furnitureworkshop.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.furnitureworkshop.demo.controller.dto.FurnitureDTO;
import project.furnitureworkshop.demo.converter.FurnitureConverter;
import project.furnitureworkshop.demo.exception.FurnitureWorkshopNotFoundException;
import project.furnitureworkshop.demo.repository.FurnitureRepository;
import project.furnitureworkshop.demo.repository.model.Furniture;
import project.furnitureworkshop.demo.service.FurnitureService;
import project.furnitureworkshop.demo.validator.FurnitureValidator;

import java.util.Collection;

@Service
@Transactional(readOnly = true)
public class FurnitureServiceImpl implements FurnitureService {


    private final FurnitureRepository furnitureRepository;

    private final FurnitureConverter furnitureConverter;

    private final FurnitureValidator furnitureValidator;


    public FurnitureServiceImpl(FurnitureRepository furnitureRepository, FurnitureConverter furnitureConverter, FurnitureValidator furnitureValidator) {
        this.furnitureRepository = furnitureRepository;
        this.furnitureConverter = furnitureConverter;
        this.furnitureValidator = furnitureValidator;
    }

    @Override
    public Collection<FurnitureDTO> getAllFurniture() {
        Collection<Furniture> all = furnitureRepository.findAll();
        return furnitureConverter.convertToDto(all);
    }

    @Override
    public FurnitureDTO getById(Integer id) {
        Furniture furniture = furnitureRepository.findById(id).orElseThrow(() -> new FurnitureWorkshopNotFoundException("Product not found:" + id));
        return furnitureConverter.convertToDto(furniture);
    }

    @Override
    @Transactional
    public Integer createFurniture(FurnitureDTO furnitureToCreate) {
        furnitureValidator.validateFurniture(furnitureToCreate);
        Furniture furniture = furnitureConverter.convertToEntity(furnitureToCreate);
        Furniture saveFurniture = furnitureRepository.save(furniture);
        return saveFurniture.getId();
    }

    @Override
    @Transactional
    public void deleteFurniture(Integer id) {
        Furniture furniture = furnitureRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Furniture not found:" + id));
        furnitureRepository.delete(furniture);

    }

    @Override
    @Transactional
    public FurnitureDTO updateFurniture(Integer id, FurnitureDTO furnitureToUpdate) {
        furnitureValidator.validateFurniture(furnitureToUpdate);
        Furniture furniture = furnitureRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Furniture not found:" + id));
        Furniture entityToUpdate = furnitureConverter.convertToEntity(furnitureToUpdate);
        entityToUpdate.setId(id);
        Furniture updateEntity = furnitureRepository.save(entityToUpdate);
        return furnitureConverter.convertToDto(updateEntity);
    }
}
