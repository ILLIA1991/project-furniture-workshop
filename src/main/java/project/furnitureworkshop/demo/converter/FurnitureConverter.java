package project.furnitureworkshop.demo.converter;

import org.springframework.stereotype.Component;
import project.furnitureworkshop.demo.controller.dto.FurnitureDTO;
import project.furnitureworkshop.demo.repository.model.Furniture;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FurnitureConverter {

    public Furniture convertToEntity(FurnitureDTO source) {
        return new Furniture(source.getId(),
                source.getName(),
                source.getDescription(),
                source.getMaterial_cube_meter());
    }

    public FurnitureDTO convertToDto(Furniture source) {
        return convertFurnitureToDto(source);
    }

    public List<FurnitureDTO> convertToDto(Collection<Furniture> source) {
        return source.stream()
                .map(this::convertFurnitureToDto)
                .collect(Collectors.toList());
    }

    public FurnitureDTO convertFurnitureToDto(Furniture source) {
        FurnitureDTO result = new FurnitureDTO();
        result.setId(source.getId());
        result.setName(source.getName());
        result.setDescription(source.getDescription());
        result.setMaterial_cube_meter(source.getMaterial_cube_meter());
        return result;
    }


}
