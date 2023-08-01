package project.furnitureworkshop.demo.validator;

import org.springframework.stereotype.Component;
import project.furnitureworkshop.demo.controller.dto.WoodSpeccyDTO;
import project.furnitureworkshop.demo.exception.ValidationException;
@Component
public class WoodSpeccyValidator {

    public void validateWoodSpecies(WoodSpeccyDTO woodSpeccyDTO) {
        if (woodSpeccyDTO.getWoodType().contains("$")) {
            throw new ValidationException("Not found type of wood");
        }
        if (woodSpeccyDTO.getHardness().contains("$")) {
            throw new ValidationException("Not found hardness");
        }
    }

}
