package project.furnitureworkshop.demo.converter;

import org.springframework.stereotype.Component;
import project.furnitureworkshop.demo.controller.dto.OrderItemDTO;
import project.furnitureworkshop.demo.repository.model.Order;
import project.furnitureworkshop.demo.repository.model.OrderItem;

import java.util.List;

@Component
public class OrdersItemsConverter {

    private final FurnitureConverter furnitureConverter;

    private final WoodSpeciesConverter woodSpeciesConverter;

    public OrdersItemsConverter(FurnitureConverter furnitureConverter, WoodSpeciesConverter woodSpeciesConverter) {
        this.furnitureConverter = furnitureConverter;
        this.woodSpeciesConverter = woodSpeciesConverter;
    }

    public List<OrderItemDTO> convertToDto(List<OrderItem> ordersItems) {
        return ordersItems.stream()
                .map(this::convertToDto)
                .toList();
    }

    private OrderItemDTO convertToDto(OrderItem orderItem) {
        OrderItemDTO result = new OrderItemDTO();
        result.setId(orderItem.getId());
        result.setOrder(result.getOrder());
        result.setFurniture(furnitureConverter.convertFurnitureToDto(orderItem.getFurniture()));
        result.setWood(woodSpeciesConverter.convertWoosSpeciesToDto(orderItem.getWoodSpeccy()));
        result.setQuantity(orderItem.getQuantity());
        result.setPrice(orderItem.getPrice());
        return result;
    }

    public List<OrderItem> convertToEntity(List<OrderItemDTO> ordersItems, Order order) {
        return ordersItems.stream()
                .map(item -> convertToEntity(item,order))
                .toList();
    }

    private OrderItem convertToEntity(OrderItemDTO source, Order order) {
        OrderItem result = new OrderItem();
        result.setId(source.getId());
        result.setOrder(order);
        result.setFurniture(furnitureConverter.convertToEntity(source.getFurniture()));
        result.setWoodSpeccy(woodSpeciesConverter.convertToEntity(source.getWood()));
        result.setQuantity(source.getQuantity());
        result.setPrice(source.getPrice());
        return result;
    }
}
