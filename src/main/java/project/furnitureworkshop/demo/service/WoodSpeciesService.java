package project.furnitureworkshop.demo.service;

import project.furnitureworkshop.demo.controller.dto.FurnitureDTO;
import project.furnitureworkshop.demo.controller.dto.WoodSpeciesDTO;

import java.util.Collection;

public interface WoodSpeciesService {

    Collection<WoodSpeciesDTO> getAllWoodSpecies();

    WoodSpeciesDTO getById(Integer id);

    Integer createWoosSpecies(WoodSpeciesDTO woodSpeciesToCreate);

    void deleteWoodSpecies(Integer id);

    WoodSpeciesDTO updateWoodSpecies(Integer id, WoodSpeciesDTO woodSpeciesToUpdate);

}
