package project.furnitureworkshop.demo.converter;

import org.springframework.stereotype.Component;
import project.furnitureworkshop.demo.controller.dto.WoodSpeccyDTO;
import project.furnitureworkshop.demo.repository.model.HardnessOfWood;
import project.furnitureworkshop.demo.repository.model.WoodSpeccy;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WoodSpeccyConverter {

    public WoodSpeccy convertToEntity(WoodSpeccyDTO source) {
        return new WoodSpeccy(source.getId(),
                source.getWoodType(),
                HardnessOfWood.valueOf(source.getHardness()),
                source.getCubicMeterPrice());
    }

    public WoodSpeccyDTO convertToDto(WoodSpeccy source) {
        return convertWoosSpeciesToDto(source);
    }

    public List<WoodSpeccyDTO> convertToDto(Collection<WoodSpeccy> source) {
        return source.stream()
                .map(this::convertWoosSpeciesToDto)
                .collect(Collectors.toList());
    }

    public WoodSpeccyDTO convertWoosSpeciesToDto(WoodSpeccy source) {
        WoodSpeccyDTO result = new WoodSpeccyDTO();
        result.setId(source.getId());
        result.setWoodType(source.getWoodType());
        result.setHardness(source.getHardness().getValue());
        result.setCubicMeterPrice(source.getCubicMeterPrice());
        return result;
    }
}
