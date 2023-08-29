package project.furnitureworkshop.demo.converter;

import org.junit.jupiter.api.Test;
import project.furnitureworkshop.demo.controller.dto.FurnitureDTO;
import project.furnitureworkshop.demo.controller.dto.OrderItemDTO;
import project.furnitureworkshop.demo.controller.dto.WoodSpeccyDTO;
import project.furnitureworkshop.demo.repository.model.Furniture;
import project.furnitureworkshop.demo.repository.model.OrderItem;
import project.furnitureworkshop.demo.repository.model.Orders;
import project.furnitureworkshop.demo.repository.model.WoodSpeccy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderItemConverterTest {

    private final FurnitureConverter furnitureConverter = mock(FurnitureConverter.class);
    private final WoodSpeccyConverter woodSpeccyConverter = mock(WoodSpeccyConverter.class);

    private final OrderItemConverter target = new OrderItemConverter(furnitureConverter, woodSpeccyConverter);

//    @Test
//    void shouldConvertToDto() {
//        //given
//        OrderItemDTO orderItem = new OrderItemDTO();
//        orderItem.setId(1);
//        orderItem.setQuantity(2);
//        orderItem.setPrice(new BigDecimal("10"));
//        FurnitureDTO furniture = new FurnitureDTO();
//        WoodSpeccyDTO woodSpeccy = new WoodSpeccyDTO();
//        orderItem.setFurniture(furniture);
//        orderItem.setWoodSpeccy(woodSpeccy);
//
//        FurnitureDTO furnitureDTO = new FurnitureDTO();
//        WoodSpeccyDTO woodSpeccyDTO = new WoodSpeccyDTO();
//
//        when(furnitureConverter.convertFurnitureToDto(furniture)).thenReturn(furnitureDTO);
//        when(woodSpeccyConverter.convertWoosSpeciesToDto(woodSpeccy)).thenReturn(woodSpeccyDTO);
//
//        //when
//        OrderItemDTO actual = (OrderItemDTO) target.convertToDto(orderItem);
//
//        //then
//        assertThat(actual.getId()).isEqualTo(orderItem.getId());
//        assertThat(actual.getQuantity()).isEqualTo(orderItem.getQuantity());
//        assertThat(actual.getPrice()).isEqualTo(orderItem.getPrice());
//        assertThat(actual.getFurniture()).isEqualTo(furnitureDTO);
//        assertThat(actual.getWood()).isEqualTo(woodSpeccyDTO);
//    }

    @Test
    void shouldConvertCollectionToDto() {
        //given
        List<OrderItem> orderItemsList = new ArrayList<>();
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setId(1);
        orderItem1.setQuantity(2);
        orderItem1.setPrice(new BigDecimal("10"));
        Furniture furniture1 = new Furniture();
        WoodSpeccy woodSpeccy1 = new WoodSpeccy();
        orderItem1.setFurniture(furniture1);
        orderItem1.setWoodSpeccy(woodSpeccy1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setId(2);
        orderItem2.setQuantity(3);
        orderItem2.setPrice(new BigDecimal("20"));
        Furniture furniture2 = new Furniture();
        WoodSpeccy woodSpeccy2 = new WoodSpeccy();
        orderItem2.setFurniture(furniture2);
        orderItem2.setWoodSpeccy(woodSpeccy2);

        orderItemsList.add(orderItem1);
        orderItemsList.add(orderItem2);

        FurnitureDTO furnitureDTO1 = new FurnitureDTO();
        WoodSpeccyDTO woodSpeccyDTO1 = new WoodSpeccyDTO();

        FurnitureDTO furnitureDTO2 = new FurnitureDTO();
        WoodSpeccyDTO woodSpeccyDTO2 = new WoodSpeccyDTO();

        when(furnitureConverter.convertFurnitureToDto(furniture1)).thenReturn(furnitureDTO1);
        when(woodSpeccyConverter.convertWoosSpeciesToDto(woodSpeccy1)).thenReturn(woodSpeccyDTO1);

        when(furnitureConverter.convertFurnitureToDto(furniture2)).thenReturn(furnitureDTO2);
        when(woodSpeccyConverter.convertWoosSpeciesToDto(woodSpeccy2)).thenReturn(woodSpeccyDTO2);

        //when
        List<OrderItemDTO> actual = target.convertToDto(orderItemsList);

        //then
        assertThat(actual).hasSize(2);

        assertThat(actual.get(0).getId()).isEqualTo(orderItemsList.get(0).getId());
        assertThat(actual.get(0).getQuantity()).isEqualTo(orderItemsList.get(0).getQuantity());
        assertThat(actual.get(0).getPrice()).isEqualTo(orderItemsList.get(0).getPrice());
        assertThat(actual.get(0).getFurniture()).isEqualTo(furnitureDTO1);
        assertThat(actual.get(0).getWood()).isEqualTo(woodSpeccyDTO1);

        assertThat(actual.get(1).getId()).isEqualTo(orderItemsList.get(1).getId());
        assertThat(actual.get(1).getQuantity()).isEqualTo(orderItemsList.get(1).getQuantity());
        assertThat(actual.get(1).getPrice()).isEqualTo(orderItemsList.get(1).getPrice());
        assertThat(actual.get(1).getFurniture()).isEqualTo(furnitureDTO2);
        assertThat(actual.get(1).getWood()).isEqualTo(woodSpeccyDTO2);
    }

//    @Test
//    void shouldConvertToEntity() {
//        //given
//        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
//        OrderItemDTO orderItemDTO = new OrderItemDTO();
//        orderItemDTO.setId(1);
//        orderItemDTO.setQuantity(2);
//        orderItemDTO.setPrice(new BigDecimal("10"));
//        FurnitureDTO furnitureDTO = new FurnitureDTO();
//        WoodSpeccyDTO woodSpeccyDTO = new WoodSpeccyDTO();
//        orderItemDTO.setFurniture(furnitureDTO);
//        orderItemDTO.setWood(woodSpeccyDTO);
//
//        Furniture furniture = new Furniture();
//        WoodSpeccy woodSpeccy = new WoodSpeccy();
//
//        when(furnitureConverter.convertToEntity(furnitureDTO)).thenReturn(furniture);
//        when(woodSpeccyConverter.convertToEntity(woodSpeccyDTO)).thenReturn(woodSpeccy);
//
//        //when
//        OrderItem actual = (OrderItem) target.convertToEntity(orderItemDTOList, new Orders());
//
//        //then
//        assertThat(actual.getId()).isEqualTo(orderItemDTO.getId());
//        assertThat(actual.getQuantity()).isEqualTo(orderItemDTO.getQuantity());
//        assertThat(actual.getPrice()).isEqualTo(orderItemDTO.getPrice());
//        assertThat(actual.getFurniture()).isEqualTo(furniture);
//        assertThat(actual.getWoodSpeccy()).isEqualTo(woodSpeccy);
//    }

}