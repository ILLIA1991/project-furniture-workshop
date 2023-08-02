package project.furnitureworkshop.demo.service;

import project.furnitureworkshop.demo.controller.dto.WoodSpeccyDTO;

import java.util.Collection;

public interface WoodSpeccyService {

    Collection<WoodSpeccyDTO> getAllWoodSpecies();

    WoodSpeccyDTO getById(Integer id);

    Integer createWoodSpeccy(WoodSpeccyDTO woodSpeciesToCreate);

    void deleteWoodSpeccy(Integer id);

    WoodSpeccyDTO updateWoodSpeccy(Integer id, WoodSpeccyDTO woodSpeciesToUpdate);

}
