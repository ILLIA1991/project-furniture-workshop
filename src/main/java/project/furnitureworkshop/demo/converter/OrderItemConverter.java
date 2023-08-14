package project.furnitureworkshop.demo.converter;

import org.springframework.stereotype.Component;
import project.furnitureworkshop.demo.controller.dto.OrderItemDTO;
import project.furnitureworkshop.demo.repository.model.Orders;
import project.furnitureworkshop.demo.repository.model.OrderItem;

import java.util.List;

@Component
public class OrderItemConverter {

    private final FurnitureConverter furnitureConverter;

    private final WoodSpeccyConverter woodSpeccyConverter;

    public OrderItemConverter(FurnitureConverter furnitureConverter, WoodSpeccyConverter woodSpeccyConverter) {
        this.furnitureConverter = furnitureConverter;
        this.woodSpeccyConverter = woodSpeccyConverter;
    }

    public List<OrderItemDTO> convertToDto(List<OrderItem> ordersItems) {
        return ordersItems.stream()
                .map(this::convertToDto)
                .toList();
    }

    private OrderItemDTO convertToDto(OrderItem orderItem) {
        OrderItemDTO result = new OrderItemDTO();
        result.setId(orderItem.getId());
        result.setFurniture(furnitureConverter.convertFurnitureToDto(orderItem.getFurniture()));
        result.setWood(woodSpeccyConverter.convertWoosSpeciesToDto(orderItem.getWoodSpeccy()));
        result.setQuantity(orderItem.getQuantity());
        result.setPrice(orderItem.getPrice());
        return result;
    }

    public List<OrderItem> convertToEntity(List<OrderItemDTO> ordersItems, Orders orders) {
        return ordersItems.stream()
                .map(item -> convertToEntity(item, orders))
                .toList();
    }

    private OrderItem convertToEntity(OrderItemDTO source, Orders orders) {
        OrderItem result = new OrderItem();
        result.setId(source.getId());
        result.setFurniture(furnitureConverter.convertToEntity(source.getFurniture()));
        result.setWoodSpeccy(woodSpeccyConverter.convertToEntity(source.getWood()));
        result.setQuantity(source.getQuantity());
        result.setPrice(source.getPrice());
        return result;
    }
}
