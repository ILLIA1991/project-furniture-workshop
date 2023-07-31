package project.furnitureworkshop.demo.service;

import project.furnitureworkshop.demo.controller.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getAll();

    Integer createOrder(OrderDTO orderDTO);

    void deleteOrder(Integer orderId);

    OrderDTO getById(Integer orderId);
}
