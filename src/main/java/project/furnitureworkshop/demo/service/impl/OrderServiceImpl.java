package project.furnitureworkshop.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.furnitureworkshop.demo.controller.dto.OrderDTO;
import project.furnitureworkshop.demo.converter.OrderConverter;
import project.furnitureworkshop.demo.exception.FurnitureWorkshopNotFoundException;
import project.furnitureworkshop.demo.repository.OrderRepository;
import project.furnitureworkshop.demo.repository.model.Orders;
import project.furnitureworkshop.demo.service.OrderService;
import project.furnitureworkshop.demo.validator.OrderValidator;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    private final OrderValidator orderValidator;

    public OrderServiceImpl(OrderRepository orderRepository, OrderConverter orderConverter, OrderValidator orderValidator) {
        this.orderRepository = orderRepository;
        this.orderConverter = orderConverter;
        this.orderValidator = orderValidator;
    }

    @Override
    public List<OrderDTO> getAll() {
        List<Orders> all = orderRepository.findAll();
        return orderConverter.convertToDto(all);
    }

    @Override
    @Transactional
    public Integer createOrder(OrderDTO orderToCreate) {
        orderValidator.validateOrder(orderToCreate);
        Orders orders = orderConverter.convertToEntity(orderToCreate);
        Orders saveOrders = orderRepository.save(orders);
        return saveOrders.getId();
    }

    @Override
    @Transactional
    public void deleteOrder(Integer orderId) {
        Orders orders = orderRepository.findById(orderId).orElseThrow(() -> new FurnitureWorkshopNotFoundException("Orders not found:" + orderId));
        orderRepository.delete(orders);

    }

    @Override
    public OrderDTO getById(Integer orderId) {
        Orders orders = orderRepository.findById(orderId).orElseThrow(() -> new FurnitureWorkshopNotFoundException("Not found orders:" + orderId));
        return orderConverter.convertToDto(orders);
    }
}
