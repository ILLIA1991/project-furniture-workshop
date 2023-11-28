package project.furnitureworkshop.demo.validator;

import org.springframework.stereotype.Component;
import project.furnitureworkshop.demo.controller.dto.FurnitureDTO;
import project.furnitureworkshop.demo.exception.ValidationException;
import project.furnitureworkshop.demo.repository.FurnitureRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class FurnitureValidator {
    private final FurnitureRepository furnitureRepository;

    public FurnitureValidator(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public void validateFurniture(FurnitureDTO furnitureDTO) {
        List<String> violations = new ArrayList<>();
        validateName(furnitureDTO, violations);
        validateMaterialConsumption(furnitureDTO, violations);
        if (!violations.isEmpty()) {
            throw new ValidationException("Provided furniture is invalid", violations);
        }
    }

    private void validateMaterialConsumption(FurnitureDTO furnitureDTO, List<String> violations) {
        if (furnitureDTO.getMaterialConsumption() == null) {
            violations.add(String.format("Material Consumption is null: %s", furnitureDTO.getMaterialConsumption()));
        } else if (furnitureDTO.getMaterialConsumption().compareTo(BigDecimal.ZERO) <= 0) {
            violations.add(String.format("Material consumption must be greater than 0: %s", furnitureDTO.getMaterialConsumption()));
        }
    }

    private void validateName(FurnitureDTO furnitureDTO, List<String> violations) {
        if (furnitureDTO.getName() == null) {
            violations.add(String.format("Name is null: %s", furnitureDTO.getName()));
        }
    }
}
