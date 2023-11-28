package project.furnitureworkshop.demo.service.impl;

import org.junit.jupiter.api.Test;
import project.furnitureworkshop.demo.controller.dto.OrderDTO;
import project.furnitureworkshop.demo.converter.OrderConverter;
import project.furnitureworkshop.demo.repository.FurnitureRepository;
import project.furnitureworkshop.demo.repository.OrderRepository;
import project.furnitureworkshop.demo.repository.WoodSpeccyRepository;
import project.furnitureworkshop.demo.repository.model.Furniture;
import project.furnitureworkshop.demo.repository.model.Orders;
import project.furnitureworkshop.demo.repository.model.WoodSpeccy;
import project.furnitureworkshop.demo.service.OrderService;
import project.furnitureworkshop.demo.validator.OrderValidator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    private final OrderRepository orderRepository = mock(OrderRepository.class);
    private final OrderConverter orderConverter = mock(OrderConverter.class);
    private final WoodSpeccyRepository woodSpeccyRepository = mock(WoodSpeccyRepository.class);
    private final FurnitureRepository furnitureRepository = mock(FurnitureRepository.class);
    private final OrderValidator orderValidator = mock(OrderValidator.class);

    private final OrderService target = new OrderServiceImpl(orderRepository,
            orderConverter,
            woodSpeccyRepository,
            furnitureRepository,
            orderValidator);

    @Test
    void shouldGetAllOrders() {
        //given
        List<Orders> orders = new ArrayList<>();
        List<OrderDTO> expected = new ArrayList<>();

        when(orderRepository.findAll()).thenReturn(orders);
        when(orderConverter.convertToDto(orders)).thenReturn(expected);

        //when
        List<OrderDTO> actual = target.getAll();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldCreateOrder() {
        //given
        OrderDTO orderDTO = new OrderDTO();
        Orders orderToSave = new Orders();
        Orders savedOrder = new Orders(88);

        when(orderConverter.convertToEntity(orderDTO)).thenReturn(orderToSave);
        when(orderRepository.save(orderToSave)).thenReturn(savedOrder);

        //when
        Integer actualId = target.createOrder(orderDTO);

        //then
        verify(orderValidator).validateOrder(orderDTO);
        verify(orderConverter).convertToEntity(orderDTO);

        assertThat(actualId).isEqualTo(savedOrder.getId());
    }

    @Test
    void shouldDeleteOrder() {
        //given
        Integer id = 88;
        Orders order = new Orders();

        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        //when
        target.deleteOrder(id);

        //then
        verify(orderRepository).delete(order);
    }

    @Test
    void shouldGetOrderById() {
        //given
        Integer id = 88;
        Orders order = new Orders();
        OrderDTO expected = new OrderDTO();

        when(orderRepository.findById(id)).thenReturn(Optional.of(order));
        when(orderConverter.convertToDto(order)).thenReturn(expected);

        //when
        OrderDTO actual = target.getById(id);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldCalculateTotalPrice() {
        //given
        Integer furnitureId = 1;
        Integer woodId = 2;
        Integer quantity = 3;
        Furniture furniture = new Furniture(new BigDecimal("10"));
        WoodSpeccy woodSpeccy = new WoodSpeccy(new BigDecimal("20"));
        BigDecimal expectedTotalPrice = new BigDecimal("600");

        when(furnitureRepository.findById(furnitureId)).thenReturn(Optional.of(furniture));
        when(woodSpeccyRepository.findById(woodId)).thenReturn(Optional.of(woodSpeccy));

        //when
        BigDecimal actualTotalPrice = target.calculateTotalPrice(furnitureId, woodId, quantity);

        //then
        assertThat(actualTotalPrice).isEqualTo(expectedTotalPrice);
    }
}