package project.furnitureworkshop.demo.converter;

import org.springframework.stereotype.Component;

import project.furnitureworkshop.demo.controller.dto.WoodSpeccyDTO;
import project.furnitureworkshop.demo.repository.model.Wood_Species;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WoodSpeciesConverter {

    public Wood_Species convertToEntity(WoodSpeccyDTO source) {
        return new Wood_Species(source.getId(),
                source.getWoodType(),
                source.getHardness(),
                source.getCubicMeterPrice());
    }

    public WoodSpeccyDTO convertToDto(Wood_Species source) {
        return convertWoosSpeciesToDto(source);
    }

    public List<WoodSpeccyDTO> convertToDto(Collection<Wood_Species> source) {
        return source.stream()
                .map(this::convertWoosSpeciesToDto)
                .collect(Collectors.toList());
    }

    public WoodSpeccyDTO convertWoosSpeciesToDto(Wood_Species source) {
        WoodSpeccyDTO result = new WoodSpeccyDTO();
        result.setId(source.getId());
        result.setWoodType(source.getWoodType());
        result.setHardness(source.getHardness());
        result.setCubicMeterPrice(source.getCubicMeterPrice());
        return result;
    }


}
