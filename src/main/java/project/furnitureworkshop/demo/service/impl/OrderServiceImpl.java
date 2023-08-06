package project.furnitureworkshop.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.furnitureworkshop.demo.controller.dto.OrderDTO;
import project.furnitureworkshop.demo.converter.OrderConverter;
import project.furnitureworkshop.demo.exception.FurnitureWorkshopNotFoundException;
import project.furnitureworkshop.demo.repository.OrderRepository;
import project.furnitureworkshop.demo.repository.model.Order;
import project.furnitureworkshop.demo.service.OrderService;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    public OrderServiceImpl(OrderRepository orderRepository, OrderConverter orderConverter) {
        this.orderRepository = orderRepository;
        this.orderConverter = orderConverter;
    }

    @Override
    public List<OrderDTO> getAll() {
        List<Order> all = orderRepository.findAll();
        return orderConverter.convertToDto(all);
    }

    @Override
    @Transactional
    public Integer createOrder(OrderDTO orderToCreate) {
        Order order = orderConverter.convertToEntity(orderToCreate);
        Order saveOrder = orderRepository.save(order);
        return saveOrder.getId();
    }

    @Override
    @Transactional
    public void deleteOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found:" + orderId));
        orderRepository.delete(order);

    }

    @Override
    public OrderDTO getById(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new FurnitureWorkshopNotFoundException("Not found order:" + orderId));
        return orderConverter.convertToDto(order);
    }
}
