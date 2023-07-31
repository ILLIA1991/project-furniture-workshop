package project.furnitureworkshop.demo.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import project.furnitureworkshop.demo.controller.dto.WoodSpeciesDTO;
import project.furnitureworkshop.demo.service.WoodSpeciesService;

import java.util.Collection;
@RestController
@RequestMapping("/woods")
public class WoodSpeciesController {

    private final WoodSpeciesService woodSpeciesService;

    public WoodSpeciesController(WoodSpeciesService woodSpeciesService) {
        this.woodSpeciesService = woodSpeciesService;
    }
    @GetMapping
    public Collection<WoodSpeciesDTO> getAll() {
        return woodSpeciesService.getAllWoodSpecies();

    }
    @GetMapping("/{id}")
    public WoodSpeciesDTO getById(@PathVariable Integer id) {
        return woodSpeciesService.getById(id);
    }
    @PostMapping
    public Integer createWoodSpecies(@RequestBody @Valid WoodSpeciesDTO woodSpeciesToCreate) {
        return woodSpeciesService.createWoosSpecies(woodSpeciesToCreate);
    }
    @DeleteMapping("/{id}")
    public void deleteWoodSpecies(@PathVariable Integer id) {
        woodSpeciesService.deleteWoodSpecies(id);
    }
    @PutMapping("/{id}")
    public WoodSpeciesDTO update(@PathVariable Integer id, @RequestBody @Valid WoodSpeciesDTO woodSpeciesToUpdate) {
        return woodSpeciesService.updateWoodSpecies(id, woodSpeciesToUpdate);
    }
}
