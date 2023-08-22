package project.furnitureworkshop.demo.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.furnitureworkshop.demo.controller.dto.WoodSpeccyDTO;
import project.furnitureworkshop.demo.exception.ValidationException;
import project.furnitureworkshop.demo.repository.WoodSpeccyRepository;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class WoodSpeccyValidatorTest {

    private final WoodSpeccyRepository woodSpeccyRepository = mock(WoodSpeccyRepository.class);

    private final WoodSpeccyValidator target = new WoodSpeccyValidator(woodSpeccyRepository);


    @Test
    @DisplayName("Validation Error should be thrown when woodType is blank")
    void shouldThrow_whenWoodTypeIsBlank() {
        //given
        WoodSpeccyDTO invalid = invalidWoodType();
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> target.validateWoodSpeccy(invalid));
        //then
        assertThat(validationException.getViolations()).contains(String.format("%s can contain only letters: %s", "type_of_wood", invalid.getWoodType()));

    }

    private WoodSpeccyDTO invalidWoodType() {
        WoodSpeccyDTO dto = new WoodSpeccyDTO();
        dto.setWoodType("");
        dto.setHardness("Hard");
        dto.setCubicMeterPrice(BigDecimal.valueOf(6));
        return dto;
    }

    @Test
    @DisplayName("Validation Error should be thrown when hardness is invalid")
    void shouldThrow_whenHardnessIsInvalid() {
        //given
        WoodSpeccyDTO invalidHardness = invalidHardness();
        String expectedMessage = String.format("%s invalid wood speccy at system", invalidHardness.getHardness());
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> target.validateWoodSpeccy(invalidHardness));
        //then
        assertThat(validationException.getViolations()).contains(expectedMessage);
    }

    private WoodSpeccyDTO invalidHardness() {
        WoodSpeccyDTO dto = new WoodSpeccyDTO();
        dto.setWoodType("Oak");
        dto.setHardness("Invalid");
        dto.setCubicMeterPrice(BigDecimal.valueOf(6));
        return dto;
    }

    @Test
    @DisplayName("Validation Error should be thrown when cubicMeterPrice is null")
    void shouldThrow_whenCubicMeterPriceIsNull() {
        //given
        WoodSpeccyDTO nullCubicMeterPrice = nullCubicMeterPrice();
        String expectedMessage = "Price is null";
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> target.validateWoodSpeccy(nullCubicMeterPrice));
        //then
        assertThat(validationException.getViolations()).contains(expectedMessage);
    }

    private WoodSpeccyDTO nullCubicMeterPrice() {
        WoodSpeccyDTO dto = new WoodSpeccyDTO();
        dto.setWoodType("Oak");
        dto.setHardness("Invalid");
        dto.setCubicMeterPrice(null);
        return dto;
    }

    @Test
    @DisplayName("Validation Error should be thrown when cubicMeterPrice is less than or equal to 0")
    void shouldThrow_whenCubicMeterPriceIsLessThanOrEqualToZero() {
        //given
        WoodSpeccyDTO invalidBigDecimalPrice = invalidBigDecimalPrice();
        invalidBigDecimalPrice.setCubicMeterPrice(BigDecimal.ZERO);
        String expectedMessage = "Our price must be greater than 0";
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> target.validateWoodSpeccy(invalidBigDecimalPrice));
        //then
        assertThat(validationException.getViolations()).contains(expectedMessage);
    }

    private WoodSpeccyDTO invalidBigDecimalPrice() {
        WoodSpeccyDTO dto = new WoodSpeccyDTO();
        dto.setWoodType("Oak");
        dto.setHardness("Invalid");
        dto.setCubicMeterPrice(BigDecimal.valueOf(6));
        return dto;
    }
}