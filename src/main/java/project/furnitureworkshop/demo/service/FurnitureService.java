package project.furnitureworkshop.demo.service;

import project.furnitureworkshop.demo.controller.dto.FurnitureDTO;

import java.util.Collection;

public interface FurnitureService {

    Collection<FurnitureDTO> getAllFurniture();

    FurnitureDTO getById(Integer id);

    Integer createFurniture(FurnitureDTO furnitureToCreate);

    void deleteFurniture(Integer id);

    FurnitureDTO updateFurniture(Integer id, FurnitureDTO furnitureToUpdate);


}
