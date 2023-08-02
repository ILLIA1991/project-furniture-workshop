package project.furnitureworkshop.demo.service;

import project.furnitureworkshop.demo.controller.dto.OrderItemDTO;

import java.util.List;

public interface OrderItemService {

    List<OrderItemDTO> getAllOrdersItems();

    Integer createOrderItem(OrderItemDTO orderItemDTO);

    void deleteOrderItem(Integer Id);

    OrderItemDTO getById(Integer Id);
}
