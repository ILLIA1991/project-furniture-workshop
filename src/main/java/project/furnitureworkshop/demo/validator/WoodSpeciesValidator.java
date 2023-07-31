package project.furnitureworkshop.demo.validator;

import org.springframework.stereotype.Component;
import project.furnitureworkshop.demo.controller.dto.WoodSpeciesDTO;
import project.furnitureworkshop.demo.exception.ValidationException;
@Component
public class WoodSpeciesValidator {

    public void validateWoodSpecies(WoodSpeciesDTO woodSpeciesDTO) {
        if (woodSpeciesDTO.getType_of_wood().contains("$")) {
            throw new ValidationException("Not found type of wood");
        }
        if (woodSpeciesDTO.getHardness().contains("$")) {
            throw new ValidationException("Not found hardness");
        }
    }

}
