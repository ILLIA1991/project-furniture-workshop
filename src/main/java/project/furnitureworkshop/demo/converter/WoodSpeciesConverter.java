package project.furnitureworkshop.demo.converter;

import org.springframework.stereotype.Component;

import project.furnitureworkshop.demo.controller.dto.WoodSpeciesDTO;

import project.furnitureworkshop.demo.repository.model.Wood_Species;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WoodSpeciesConverter {

    public Wood_Species convertToEntity(WoodSpeciesDTO source) {
        return new Wood_Species(source.getId(),
                source.getType_of_wood(),
                source.getHardness(),
                source.getPrice_cubic_meter());
    }

    public WoodSpeciesDTO convertToDto(Wood_Species source) {
        return convertWoosSpeciesToDto(source);
    }

    public List<WoodSpeciesDTO> convertToDto(Collection<Wood_Species> source) {
        return source.stream()
                .map(this::convertWoosSpeciesToDto)
                .collect(Collectors.toList());
    }

    public WoodSpeciesDTO convertWoosSpeciesToDto(Wood_Species source) {
        WoodSpeciesDTO result = new WoodSpeciesDTO();
        result.setId(source.getId());
        result.setType_of_wood(source.getType_of_wood());
        result.setHardness(source.getHardness());
        result.setPrice_cubic_meter(source.getPrice_cubic_meter());
        return result;
    }


}
