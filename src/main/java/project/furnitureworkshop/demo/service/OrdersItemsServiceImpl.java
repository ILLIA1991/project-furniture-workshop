package project.furnitureworkshop.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.furnitureworkshop.demo.controller.dto.OrdersItemsDTO;
import project.furnitureworkshop.demo.converter.OrdersItemsConverter;
import project.furnitureworkshop.demo.repository.SpringDataOrdersItemsRepository;
import project.furnitureworkshop.demo.repository.model.OrdersItems;

import java.util.Collection;
import java.util.List;
@Service
@Transactional
public class OrdersItemsServiceImpl implements OrdersItemsService{

    private final SpringDataOrdersItemsRepository ordersItemsRepository;
    private final OrdersItemsConverter ordersItemsConverter;

    public OrdersItemsServiceImpl(SpringDataOrdersItemsRepository ordersItemsRepository, OrdersItemsConverter ordersItemsConverter) {
        this.ordersItemsRepository = ordersItemsRepository;
        this.ordersItemsConverter = ordersItemsConverter;
    }


    @Override
    public List<OrdersItemsDTO> getAllOrdersItems() {
        List<OrdersItems> all = ordersItemsRepository.findAll();
        return ordersItemsConverter.convertToDto(all);
    }

    @Override
    public Integer createOrdersItems(OrdersItemsDTO ordersItemsDTO) {
        return null;
    }

    @Override
    public void deleteOrdersItems(Integer Id) {

    }

    @Override
    public OrdersItemsDTO getById(Integer Id) {
        OrdersItems ordersItems = ordersItemsRepository.findById()
    }
}
