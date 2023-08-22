package project.furnitureworkshop.demo.validator;

import org.springframework.stereotype.Component;
import project.furnitureworkshop.demo.controller.dto.WoodSpeccyDTO;
import project.furnitureworkshop.demo.exception.ValidationException;
import project.furnitureworkshop.demo.repository.WoodSpeccyRepository;
import project.furnitureworkshop.demo.repository.model.HardnessOfWood;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;


@Component
public class WoodSpeccyValidator {

    private final WoodSpeccyRepository woodSpeccyRepository;

    public WoodSpeccyValidator(WoodSpeccyRepository woodSpeccyRepository) {
        this.woodSpeccyRepository = woodSpeccyRepository;
    }

    public void validateWoodSpeccy(WoodSpeccyDTO woodSpeccyDTO) {

        List<String> violations = new ArrayList<>();
        validateLetterField(woodSpeccyDTO.getWoodType(), violations);
        validateHardness(woodSpeccyDTO, violations);
        validateCubicMeterPrice(woodSpeccyDTO, violations);


        if (!violations.isEmpty()) {
            throw new ValidationException("Provided wood speccy is invalid!", violations);
        }
    }

    public void validateCubicMeterPrice(WoodSpeccyDTO woodSpeccyDTO, List<String> violations) {
        if (woodSpeccyDTO.getCubicMeterPrice() == null) {
            violations.add("Price is null");
        } else if (woodSpeccyDTO.getCubicMeterPrice().compareTo(BigDecimal.ZERO) <= 0) {
            violations.add("Our price must be greater than 0");
        }

    }

    public void validateHardness(WoodSpeccyDTO woodSpeccyDTO, List<String> violations) {
        if (HardnessOfWood.findByValue(woodSpeccyDTO.getHardness()) == null) {
            violations.add(String.format("%s invalid wood speccy at system", woodSpeccyDTO.getHardness()));
        }
    }


    private void validateLetterField(String value, List<String> violations) {

        if (isBlank(value)) {
            violations.add(String.format("%s can contain only letters: %s", "type_of_wood", value));
        }
    }


}
