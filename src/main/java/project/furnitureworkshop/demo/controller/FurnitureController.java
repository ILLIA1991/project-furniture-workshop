package project.furnitureworkshop.demo.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import project.furnitureworkshop.demo.controller.dto.FurnitureDTO;
import project.furnitureworkshop.demo.service.FurnitureService;

import java.util.Collection;

@RestController
@RequestMapping("/furniture")
public class FurnitureController {

    private final FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @GetMapping
    public Collection<FurnitureDTO> getAll() {
        return furnitureService.getAllFurniture();
    }

    @GetMapping("/{id}")
    public FurnitureDTO getById(@PathVariable Integer id) {
        return furnitureService.getById(id);
    }

    @PostMapping
    public Integer createFurniture(@RequestBody @Valid FurnitureDTO furnitureToCreate) {
        return furnitureService.createFurniture(furnitureToCreate);
    }

    @DeleteMapping("/{id}")
    public void deleteFurniture(@PathVariable Integer id) {
        furnitureService.deleteFurniture(id);
    }

    @PutMapping("/{id}")
    public FurnitureDTO update(@PathVariable Integer id, @RequestBody @Valid FurnitureDTO furnitureToUpdate) {
        return furnitureService.updateFurniture(id, furnitureToUpdate);
    }


}
