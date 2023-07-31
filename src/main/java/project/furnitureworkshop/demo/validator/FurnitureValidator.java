package project.furnitureworkshop.demo.validator;

import org.springframework.stereotype.Component;
import project.furnitureworkshop.demo.controller.dto.FurnitureDTO;
import project.furnitureworkshop.demo.exception.ValidationException;

@Component
public class FurnitureValidator {

    public void validateFurniture(FurnitureDTO furnitureDTO) {
        if (furnitureDTO.getName().contains("$")) {
            throw new ValidationException("not found name");
        }
        if (furnitureDTO.getDescription().contains("$")) {
            throw new ValidationException("Not found description");
        }
    }

}
