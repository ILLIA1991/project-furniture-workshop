package project.furnitureworkshop.demo.converter;

import org.springframework.stereotype.Component;
import project.furnitureworkshop.demo.controller.dto.OrdersItemsDTO;
import project.furnitureworkshop.demo.repository.model.Order;
import project.furnitureworkshop.demo.repository.model.OrdersItems;

import java.util.List;

@Component
public class OrdersItemsConverter {

    private final FurnitureConverter furnitureConverter;

    private final WoodSpeciesConverter woodSpeciesConverter;

    public OrdersItemsConverter(FurnitureConverter furnitureConverter, WoodSpeciesConverter woodSpeciesConverter) {
        this.furnitureConverter = furnitureConverter;
        this.woodSpeciesConverter = woodSpeciesConverter;
    }

    public List<OrdersItemsDTO> convertToDto(List<OrdersItems> ordersItems) {
        return ordersItems.stream()
                .map(this::convertToDto)
                .toList();
    }

    private OrdersItemsDTO convertToDto(OrdersItems ordersItems) {
        OrdersItemsDTO result = new OrdersItemsDTO();
        result.setId(ordersItems.getId());
        result.setOrder(result.getOrder());
        result.setFurniture(furnitureConverter.convertFurnitureToDto(ordersItems.getFurniture()));
        result.setWoods(woodSpeciesConverter.convertWoosSpeciesToDto(ordersItems.getWoodSpecies()));
        result.setQuantity(ordersItems.getQuantity());
        result.setPrice(ordersItems.getPrice());
        return result;
    }

    public List<OrdersItems> convertToEntity(List<OrdersItemsDTO> ordersItems, Order order) {
        return ordersItems.stream()
                .map(item -> convertToEntity(item,order))
                .toList();
    }

    private OrdersItems convertToEntity(OrdersItemsDTO source, Order order) {
        OrdersItems result = new OrdersItems();
        result.setId(source.getId());
        result.setOrder(order);
        result.setFurniture(furnitureConverter.convertToEntity(source.getFurniture()));
        result.setWoodSpecies(woodSpeciesConverter.convertToEntity(source.getWoods()));
        result.setQuantity(source.getQuantity());
        result.setPrice(source.getPrice());
        return result;
    }
}
