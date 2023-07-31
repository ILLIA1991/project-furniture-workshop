package project.furnitureworkshop.demo.service;

import project.furnitureworkshop.demo.controller.dto.OrdersItemsDTO;

import java.util.List;

public interface OrdersItemsService {

    List<OrdersItemsDTO> getAllOrdersItems();

    Integer createOrdersItems(OrdersItemsDTO ordersItemsDTO);

    void deleteOrdersItems(Integer Id);

    OrdersItemsDTO getById(Integer Id);
}
