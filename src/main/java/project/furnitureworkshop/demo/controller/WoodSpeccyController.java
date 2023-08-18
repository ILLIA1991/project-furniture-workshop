package project.furnitureworkshop.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import project.furnitureworkshop.demo.controller.dto.WoodSpeccyDTO;
import project.furnitureworkshop.demo.service.WoodSpeccyService;

import java.util.Collection;
@Tag(name = "Woods management API", description = "API for CRUD operations with woods")
@RestController
@RequestMapping("/woods")
public class WoodSpeccyController {
    private final WoodSpeccyService woodSpeccyService;
    public WoodSpeccyController(WoodSpeccyService woodSpeccyService) {
        this.woodSpeccyService = woodSpeccyService;
    }
    @Operation(description = "Search for all relevant types of wood. Access without restrictions.")
    @GetMapping
    public Collection<WoodSpeccyDTO> getAll() {
        return woodSpeccyService.getAllWoodSpecies();
    }
    @Operation(description = "Search for wood by ID. Access without restrictions.")
    @GetMapping("/{id}")
    public WoodSpeccyDTO getById(@PathVariable Integer id) {
        return woodSpeccyService.getById(id);
    }
    @Operation(description = "Creation of a new type of wood, in agreement with a new supplier. Access only to the admin.")
    @PostMapping
    public Integer createWoodSpecies(@RequestBody WoodSpeccyDTO woodSpeciesToCreate) {
        return woodSpeccyService.createWoodSpeccy(woodSpeciesToCreate);
    }
    @Operation(description = "Removal of the type of wood, if not available (in stock). Access only to the admin.")
    @DeleteMapping("/{id}")
    public void deleteWoodSpecies(@PathVariable Integer id) {
        woodSpeccyService.deleteWoodSpeccy(id);
    }
    @Operation(description = "Updating data on wood available. Access only to the admin.")
    @PutMapping("/{id}")
    public WoodSpeccyDTO update(@PathVariable Integer id, @RequestBody WoodSpeccyDTO woodSpeciesToUpdate) {
        return woodSpeccyService.updateWoodSpeccy(id, woodSpeciesToUpdate);
    }
}
