package project.furnitureworkshop.demo.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import project.furnitureworkshop.demo.controller.dto.OrderDTO;
import project.furnitureworkshop.demo.repository.model.Order;

import java.util.List;

@Component
public class OrderConverter {

    private static final Logger logger = LoggerFactory.getLogger(OrderConverter.class);

    private final ClientsConverter clientsConverter;
    private final OrdersItemsConverter ordersItemsConverter;

    public OrderConverter(ClientsConverter clientsConverter, OrdersItemsConverter ordersItemsConverter) {
        this.clientsConverter = clientsConverter;
        this.ordersItemsConverter = ordersItemsConverter;
    }

    public OrderDTO convertToDto(Order order) {
        return convert(order);
    }

    public List<OrderDTO> convertToDto(List<Order> all) {
        return all.stream()
                .map(this::convert)
                .toList();
    }
    public Order convertToEntity(OrderDTO source) {
        Order result = new Order();
        result.setClients(clientsConverter.convertToEntity(source.getClients()));
        result.setOrdersItems(ordersItemsConverter.convertToEntity(source.getOrdersItems(), result));
        result.setDateOrder(source.getDateOrder());
        return result;
    }

    private OrderDTO convert(Order source) {
        OrderDTO target = new OrderDTO();
        target.setId(source.getId());
        target.setClients(clientsConverter.convertToDto(source.getClients()));
        target.setDateOrder(source.getDateOrder());
        logger.debug("Now we will download the lines of the order....");
        target.setOrdersItems(ordersItemsConverter.convertToDto(source.getOrdersItems()));
        return target;
    }
}
