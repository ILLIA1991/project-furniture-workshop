package project.furnitureworkshop.demo.service;

import project.furnitureworkshop.demo.controller.dto.OrderDTO;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    List<OrderDTO> getAll();

    Integer createOrder(OrderDTO orderDTO);

    void deleteOrder(Integer orderId);

    OrderDTO getById(Integer orderId);

    BigDecimal calculateTotalPrice(Integer furnitureId, Integer woodId, Integer quantity);
}
