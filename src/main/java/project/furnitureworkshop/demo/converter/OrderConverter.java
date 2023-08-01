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

    private final ClientConverter clientConverter;
    private final OrderItemConverter orderItemConverter;

    public OrderConverter(ClientConverter clientConverter, OrderItemConverter orderItemConverter) {
        this.clientConverter = clientConverter;
        this.orderItemConverter = orderItemConverter;
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
        result.setClients(clientConverter.convertToEntity(source.getClients()));
        result.setOrdersItems(orderItemConverter.convertToEntity(source.getOrdersItems(), result));
        result.setOrderDate(source.getOrderDate());
        return result;
    }

    private OrderDTO convert(Order source) {
        OrderDTO target = new OrderDTO();
        target.setId(source.getId());
        target.setClients(clientConverter.convertToDto(source.getClients()));
        target.setOrderDate(source.getOrderDate());
        logger.debug("Now we will download the lines of the order....");
        target.setOrdersItems(orderItemConverter.convertToDto(source.getOrdersItems()));
        return target;
    }
}
