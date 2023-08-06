package project.furnitureworkshop.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.furnitureworkshop.demo.controller.dto.OrderItemDTO;
import project.furnitureworkshop.demo.converter.OrderItemConverter;
import project.furnitureworkshop.demo.repository.OrderItemRepository;
import project.furnitureworkshop.demo.repository.model.OrderItem;
import project.furnitureworkshop.demo.service.OrderItemService;

import java.util.List;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository ordersItemsRepository;
    private final OrderItemConverter orderItemConverter;

    public OrderItemServiceImpl(OrderItemRepository ordersItemsRepository, OrderItemConverter orderItemConverter) {
        this.ordersItemsRepository = ordersItemsRepository;
        this.orderItemConverter = orderItemConverter;
    }


    @Override
    public List<OrderItemDTO> getAllOrdersItems() {
        List<OrderItem> all = ordersItemsRepository.findAll();
        return orderItemConverter.convertToDto(all);
    }

    @Override
    public Integer createOrderItem(OrderItemDTO orderItemDTO) {
        return null;
    }

    @Override
    public void deleteOrderItem(Integer Id) {

    }

    @Override
    public OrderItemDTO getById(Integer Id) {
        return null;
    }
}
