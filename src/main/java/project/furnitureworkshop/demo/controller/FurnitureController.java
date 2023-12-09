package project.furnitureworkshop.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import project.furnitureworkshop.demo.controller.dto.FurnitureDTO;
import project.furnitureworkshop.demo.service.FurnitureService;

import java.util.Collection;

@Tag(name = "Furniture management API", description = "API for CRUD operations with furniture")
@RestController
@RequestMapping("/furniture")
public class FurnitureController {
    private final FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @Operation(description = "Search for all current types of furniture. This procedure is freely available to everyone.")
    @GetMapping
    public Collection<FurnitureDTO> getAll() {
        return furnitureService.getAllFurniture();
    }

    @Operation(description = "Search for specific types of furniture by ID. The client and the admin have access.")
    @GetMapping("/{id}")
    public FurnitureDTO getById(@PathVariable Integer id) {
        return furnitureService.getById(id);
    }

    @Operation(description = "Filling with new types of furniture. Only the admin has access.")
    @PostMapping
    public Integer createFurniture(@RequestBody FurnitureDTO furnitureToCreate) {
        return furnitureService.createFurniture(furnitureToCreate);
    }

    @Operation(description = "Removal of irrelevant or outdated furniture models. Access only to the admin.")
    @DeleteMapping("/{id}")
    public void deleteFurniture(@PathVariable Integer id) {
        furnitureService.deleteFurniture(id);
    }

    @Operation(description = "Updating existing data on the types of furniture in our catalog. Access only to the admin.")
    @PutMapping("/{id}")
    public FurnitureDTO update(@PathVariable Integer id, @RequestBody FurnitureDTO furnitureToUpdate) {
        return furnitureService.updateFurniture(id, furnitureToUpdate);
    }
}
