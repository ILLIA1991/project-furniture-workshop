package project.furnitureworkshop.demo.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.furnitureworkshop.demo.controller.dto.FurnitureDTO;
import project.furnitureworkshop.demo.exception.ValidationException;
import project.furnitureworkshop.demo.repository.FurnitureRepository;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class FurnitureValidatorTest {

    private final FurnitureRepository furnitureRepository = mock(FurnitureRepository.class);

    private final FurnitureValidator target = new FurnitureValidator(furnitureRepository);

    @Test
    @DisplayName("Validation Error should not be thrown when input is valid")
    void shouldNotThrow_whenFurnitureIsValid() {
        //give
        FurnitureDTO valid = validFurniture();
        //when
        assertDoesNotThrow(() -> target.validateFurniture(valid));
        //then
    }

    private FurnitureDTO validFurniture() {
        FurnitureDTO dto = new FurnitureDTO();
        dto.setName("Chair");
        dto.setDescription("Kitchen chair");
        dto.setMaterialConsumption(BigDecimal.valueOf(2));
        return dto;

    }

    @Test
    @DisplayName("Validation Error should be thrown when name is null")
    void shouldThrow_whenNameIsNull() {
        //give
        FurnitureDTO invalidName = invalidNameFurniture();
        String expectedMessage = String.format("Name is null: %s", invalidName.getName());
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> target.validateFurniture(invalidName));
        //then
        assertThat(validationException.getViolations()).contains(expectedMessage);

    }

    private FurnitureDTO invalidNameFurniture() {
        FurnitureDTO dto = new FurnitureDTO();
        dto.setName(null);
        dto.setDescription("Kitchen chair");
        dto.setMaterialConsumption(BigDecimal.valueOf(2));
        return dto;
    }

    @Test
    @DisplayName("Validation Error should be thrown when material consumption is null")
    void shouldThrow_whenMaterialConsumptionIsNull() {
        //given
        FurnitureDTO invalidMaterialConsumption = invalidMaterialConsumption();
        String expectedMessage = String.format("Material Consumption is null: %s", invalidMaterialConsumption.getMaterialConsumption());
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> target.validateFurniture(invalidMaterialConsumption));
        //then
        assertThat(validationException.getViolations()).contains(expectedMessage);
    }

    private FurnitureDTO invalidMaterialConsumption() {
        FurnitureDTO dto = new FurnitureDTO();
        dto.setName("Chair");
        dto.setDescription("Kitchen chair");
        return dto;
    }

    @Test
    @DisplayName("Validation Error should be thrown when material consumption is less than or equal to 0")
    void shouldThrow_whenMaterialConsumptionIsLessThanOrEqualToZero() {
        //give
        FurnitureDTO invalidMaterial = invalidMaterial();
        invalidMaterial.setMaterialConsumption(BigDecimal.ZERO);
        String expectedMessage = String.format("Material consumption must be greater than 0: %s", invalidMaterial.getMaterialConsumption());
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> target.validateFurniture(invalidMaterial));
        //then
        assertThat(validationException.getViolations()).contains(expectedMessage);

    }

    private FurnitureDTO invalidMaterial() {
        FurnitureDTO dto = new FurnitureDTO();
        dto.setName("Chair");
        dto.setDescription("Kitchen chair");
        dto.setMaterialConsumption(BigDecimal.valueOf(2));
        return dto;
    }


}