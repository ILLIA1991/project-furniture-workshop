package project.furnitureworkshop.demo.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.furnitureworkshop.demo.controller.dto.ClientDTO;
import project.furnitureworkshop.demo.controller.dto.OrderDTO;
import project.furnitureworkshop.demo.controller.dto.OrderItemDTO;
import project.furnitureworkshop.demo.controller.dto.WoodSpeccyDTO;
import project.furnitureworkshop.demo.exception.ValidationException;
import project.furnitureworkshop.demo.repository.OrderRepository;

import java.math.BigDecimal;
import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class OrderValidatorTest {

    private final OrderRepository orderRepository = mock(OrderRepository.class);

    private final OrderValidator target = new OrderValidator(orderRepository);


    @Test
    @DisplayName("Validation Error should be thrown when client id is null")
    void shouldThrow_whenClientIdIsNull() {
        //given
        OrderDTO invalidClientId = invalidClientId();
        String expectedMessage = "The client could not be found";
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> target.validateOrder(invalidClientId));
        //then
        assertThat(validationException.getViolations()).contains(expectedMessage);
    }

    private OrderDTO invalidClientId() {
        OrderDTO dto = new OrderDTO();
        dto.setOrderDate(Date.valueOf("2022-01-01"));
        dto.setClient(new ClientDTO());
        return dto;
    }

    @Test
    @DisplayName("Validation Error should be thrown when order date is null")
    void shouldThrow_whenOrderDateIsNull() {
        //given
        OrderDTO invalidOrderDate = invalidOrderDate();
        String expectedMessage = "Date of order is null";
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> target.validateOrder(invalidOrderDate));
        //then
        assertThat(validationException.getViolations()).contains(expectedMessage);
    }

    private OrderDTO invalidOrderDate() {
        OrderDTO dto = new OrderDTO();
        dto.setClient(new ClientDTO());
        return dto;
    }

    @Test
    @DisplayName("Validation Error should be thrown when order item is null or empty")
    void shouldThrow_whenOrderItemIsNull() {
        //given
        OrderDTO invalidOrderItem = invalidOrderItem();
        String expectedMessage = "Order item is null or empty";
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> target.validateOrder(invalidOrderItem));
        //then
        assertThat(validationException.getViolations()).contains(expectedMessage);
    }

    private OrderDTO invalidOrderItem() {
        OrderDTO dto = new OrderDTO();
        dto.setOrderDate(Date.valueOf("2022-01-01"));
        dto.setClient(new ClientDTO());
        return dto;
    }

    @Test
    @DisplayName("Validation Error should be thrown when furniture id is null")
    void shouldThrow_whenFurnitureIdIsNull() {
        //given
        OrderDTO invalidFurnitureId = invalidFurnitureId();
        String expectedMessage = "The furniture could not be found";
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> target.validateOrder(invalidFurnitureId));
        //then
        assertThat(validationException.getViolations()).contains(expectedMessage);
    }

    private OrderDTO invalidFurnitureId() {
        OrderDTO dto = new OrderDTO();
        dto.setOrderDate(Date.valueOf("2022-01-01"));
        dto.setClient(new ClientDTO());
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setPrice(BigDecimal.TEN);
        orderItemDTO.setQuantity(1);
        orderItemDTO.setWood(new WoodSpeccyDTO());
        dto.getOrdersItems().add(orderItemDTO);
        return dto;
    }

    @Test
    @DisplayName("Validation Error should be thrown when wood id is null")
    void shouldThrow_whenWoodIdIsNull() {
        //given
        OrderDTO invalidWoodId = invalidWoodId();
        String expectedMessage = "The wood could not be found";
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> target.validateOrder(invalidWoodId));
        //then
        assertThat(validationException.getViolations()).contains(expectedMessage);
    }

    private OrderDTO invalidWoodId() {
        OrderDTO dto = new OrderDTO();
        dto.setOrderDate(Date.valueOf("2022-01-01"));
        dto.setClient(new ClientDTO());
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setPrice(BigDecimal.TEN);
        orderItemDTO.setQuantity(1);
        dto.getOrdersItems().add(orderItemDTO);
        return dto;
    }


    @Test
    @DisplayName("Validation Error should be thrown when quantity is null")
    void shouldThrow_whenQuantityIsNull() {
        //given
        OrderDTO invalidQuantityOrderItem = invalidQuantityOrderItem();
        String expectedMessage = "Quantity is null";
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> target.validateOrder(invalidQuantityOrderItem));
        //then
        assertThat(validationException.getViolations()).contains(expectedMessage);
    }

    private OrderDTO invalidQuantityOrderItem() {
        OrderDTO dto = new OrderDTO();
        dto.setOrderDate(Date.valueOf("2022-01-01"));
        dto.setClient(new ClientDTO());
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setPrice(BigDecimal.TEN);
        dto.getOrdersItems().add(orderItemDTO);
        return dto;
    }

    @Test
    @DisplayName("Validation Error should be thrown when quantity is less than or equal to zero")
    void shouldThrow_whenQuantityIsLessThanOrEqualToZero() {
        //given
        OrderDTO invalidQuantity = invalidQuantity();
        String expectedMessage = "The quantity must not be zero";
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> target.validateOrder(invalidQuantity));
        //then
        assertThat(validationException.getViolations()).contains(expectedMessage);
    }

    private OrderDTO invalidQuantity() {
        OrderDTO dto = new OrderDTO();
        dto.setOrderDate(Date.valueOf("2022-01-01"));
        dto.setClient(new ClientDTO());
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setPrice(BigDecimal.TEN);
        orderItemDTO.setQuantity(0);
        dto.getOrdersItems().add(orderItemDTO);
        return dto;
    }

    @Test
    @DisplayName("Validation Error should be thrown when price is null")
    void shouldThrow_whenPriceIsNull() {
        //given
        OrderDTO invalidOrderItemPrice = invalidOrderItemPrice();
        String expectedMessage = "This price is null";
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> target.validateOrder(invalidOrderItemPrice));
        //then
        assertThat(validationException.getViolations()).contains(expectedMessage);
    }

    private OrderDTO invalidOrderItemPrice() {
        OrderDTO dto = new OrderDTO();
        dto.setOrderDate(Date.valueOf("2022-01-01"));
        dto.setClient(new ClientDTO());
        dto.getOrdersItems().add(new OrderItemDTO());
        return dto;
    }

    @Test
    @DisplayName("Validation Error should be thrown when price is less than or equal to zero")
    void shouldThrow_whenPriceIsLessThanOrEqualToZero() {
        //given
        OrderDTO invalidPrice = invalidPrice();
        String expectedMessage = "This price must be greater than zero";
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> target.validateOrder(invalidPrice));
        //then
        assertThat(validationException.getViolations()).contains(expectedMessage);
    }

    private OrderDTO invalidPrice() {
        OrderDTO dto = new OrderDTO();
        dto.setOrderDate(Date.valueOf("2022-01-01"));
        dto.setClient(new ClientDTO());
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setPrice(BigDecimal.ZERO);
        dto.getOrdersItems().add(orderItemDTO);
        return dto;
    }
}