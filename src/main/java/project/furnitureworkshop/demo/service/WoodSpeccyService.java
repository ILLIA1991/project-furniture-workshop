package project.furnitureworkshop.demo.service;

import project.furnitureworkshop.demo.controller.dto.WoodSpeccyDTO;

import java.util.Collection;

public interface WoodSpeccyService {

    Collection<WoodSpeccyDTO> getAllWoodSpecies();

    WoodSpeccyDTO getById(Integer id);

    Integer createWoosSpecies(WoodSpeccyDTO woodSpeciesToCreate);

    void deleteWoodSpecies(Integer id);

    WoodSpeccyDTO updateWoodSpeccy(Integer id, WoodSpeccyDTO woodSpeciesToUpdate);

}
