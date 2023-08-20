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
            throw new ValidationException("Provided furniture is invalid");
        }

    }

    private void validateMaterialConsumption(FurnitureDTO furnitureDTO, List<String> violations) {
        if (furnitureDTO.getMaterialConsumption() == null) {
            violations.add("Material Consumption is null");
        } else if (furnitureDTO.getMaterialConsumption().compareTo(BigDecimal.ZERO) <= 0) {
            violations.add("Material consumption must be greater than 0");
        }
    }


    private void validateName(FurnitureDTO furnitureDTO, List<String> violations) {
        if (furnitureDTO.getName() == null) {
            violations.add("Name is null");
        }
    }

}
