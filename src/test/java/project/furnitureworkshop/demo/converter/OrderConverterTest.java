package project.furnitureworkshop.demo.converter;

import org.junit.jupiter.api.Test;
import project.furnitureworkshop.demo.controller.dto.ClientDTO;
import project.furnitureworkshop.demo.controller.dto.OrderDTO;
import project.furnitureworkshop.demo.controller.dto.OrderItemDTO;
import project.furnitureworkshop.demo.repository.model.Client;
import project.furnitureworkshop.demo.repository.model.OrderItem;
import project.furnitureworkshop.demo.repository.model.Orders;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderConverterTest {

    private final ClientConverter clientConverter = mock(ClientConverter.class);
    private final OrderItemConverter orderItemConverter = mock(OrderItemConverter.class);

    private final OrderConverter target = new OrderConverter(clientConverter, orderItemConverter);

    @Test
    void shouldConvertToDto() {
        //given
        Orders orders = new Orders();
        orders.setId(1);
        orders.setOrderDate(new Date(2023-07-21));
        Client client = new Client();
        List<OrderItem> orderItems = new ArrayList<>();
        orders.setClients(client);
        orders.setOrdersItems(orderItems);

        ClientDTO clientDTO = new ClientDTO();
        List<OrderItemDTO> orderItemDTOs = new ArrayList<>();

        when(clientConverter.convertToDto(client)).thenReturn(clientDTO);
        when(orderItemConverter.convertToDto(orderItems)).thenReturn(orderItemDTOs);

        //when
        OrderDTO actual = target.convertToDto(orders);

        //then
        assertThat(actual.getId()).isEqualTo(orders.getId());
        assertThat(actual.getOrderDate()).isEqualTo(orders.getOrderDate());
        assertThat(actual.getClient()).isEqualTo(clientDTO);
        assertThat(actual.getOrdersItems()).isEqualTo(orderItemDTOs);
    }

    @Test
    void shouldConvertCollectionToDto() {
        //given
        List<Orders> ordersList = new ArrayList<>();
        Orders orders1 = new Orders();
        orders1.setId(1);
        orders1.setOrderDate(new Date(2023-07-21));
        Client client1 = new Client();
        List<OrderItem> orderItems1 = new ArrayList<>();
        orders1.setClients(client1);
        orders1.setOrdersItems(orderItems1);

        Orders orders2 = new Orders();
        orders2.setId(2);
        orders2.setOrderDate(new Date(2023-07-21));
        Client client2 = new Client();
        List<OrderItem> orderItems2 = new ArrayList<>();
        orders2.setClients(client2);
        orders2.setOrdersItems(orderItems2);

        ordersList.add(orders1);
        ordersList.add(orders2);

        ClientDTO clientDTO1 = new ClientDTO();
        List<OrderItemDTO> orderItemDTOs1 = new ArrayList<>();

        ClientDTO clientDTO2 = new ClientDTO();
        List<OrderItemDTO> orderItemDTOs2 = new ArrayList<>();

        when(clientConverter.convertToDto(client1)).thenReturn(clientDTO1);
        when(orderItemConverter.convertToDto(orderItems1)).thenReturn(orderItemDTOs1);

        when(clientConverter.convertToDto(client2)).thenReturn(clientDTO2);
        when(orderItemConverter.convertToDto(orderItems2)).thenReturn(orderItemDTOs2);

        //when
        List<OrderDTO> actual = target.convertToDto(ordersList);

        //then
        assertThat(actual).hasSize(2);

        assertThat(actual.get(0).getId()).isEqualTo(ordersList.get(0).getId());
        assertThat(actual.get(0).getOrderDate()).isEqualTo(ordersList.get(0).getOrderDate());
        assertThat(actual.get(0).getClient()).isEqualTo(clientDTO1);
        assertThat(actual.get(0).getOrdersItems()).isEqualTo(orderItemDTOs1);

        assertThat(actual.get(1).getId()).isEqualTo(ordersList.get(1).getId());
        assertThat(actual.get(1).getOrderDate()).isEqualTo(ordersList.get(1).getOrderDate());
        assertThat(actual.get(1).getClient()).isEqualTo(clientDTO2);
        assertThat(actual.get(1).getOrdersItems()).isEqualTo(orderItemDTOs2);
    }

    @Test
    void shouldConvertToEntity() {
        //given
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1);
        orderDTO.setOrderDate(new Date(2023-07-21));
        ClientDTO clientDTO = new ClientDTO();
        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
        orderDTO.setClient(clientDTO);
        orderDTO.setOrdersItems(orderItemDTOS);

        Client client = new Client();
        List<OrderItem> orderItems = new ArrayList<>();

        when(clientConverter.convertToEntity(clientDTO)).thenReturn(client);
        when(orderItemConverter.convertToEntity(any(), any())).thenReturn(orderItems);

        //when
        Orders actual = target.convertToEntity(orderDTO);

        //then
        verify(orderItemConverter).convertToEntity(eq(orderItemDTOS), eq(actual));

        assertThat(actual.getClients()).isEqualTo(client);
        assertThat(actual.getOrderDate()).isEqualTo(orderDTO.getOrderDate());
        assertThat(actual.getOrdersItems()).isEqualTo(orderItems);
    }

}