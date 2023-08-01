package project.furnitureworkshop.demo.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import project.furnitureworkshop.demo.controller.dto.WoodSpeccyDTO;
import project.furnitureworkshop.demo.service.WoodSpeccyService;

import java.util.Collection;
@RestController
@RequestMapping("/woods")
public class WoodSpeccyController {

    private final WoodSpeccyService woodSpeccyService;

    public WoodSpeccyController(WoodSpeccyService woodSpeccyService) {
        this.woodSpeccyService = woodSpeccyService;
    }
    @GetMapping
    public Collection<WoodSpeccyDTO> getAll() {
        return woodSpeccyService.getAllWoodSpecies();

    }
    @GetMapping("/{id}")
    public WoodSpeccyDTO getById(@PathVariable Integer id) {
        return woodSpeccyService.getById(id);
    }
    @PostMapping
    public Integer createWoodSpecies(@RequestBody @Valid WoodSpeccyDTO woodSpeciesToCreate) {
        return woodSpeccyService.createWoosSpecies(woodSpeciesToCreate);
    }
    @DeleteMapping("/{id}")
    public void deleteWoodSpecies(@PathVariable Integer id) {
        woodSpeccyService.deleteWoodSpecies(id);
    }
    @PutMapping("/{id}")
    public WoodSpeccyDTO update(@PathVariable Integer id, @RequestBody @Valid WoodSpeccyDTO woodSpeciesToUpdate) {
        return woodSpeccyService.updateWoodSpeccy(id, woodSpeciesToUpdate);
    }
}
