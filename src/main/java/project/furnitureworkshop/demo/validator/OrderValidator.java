package project.furnitureworkshop.demo.validator;

/*
OrderDTO
    - client  - client.getId() != null
    - orderDate - != null
    - orderItems  - не пустой массив
    OrderItemDTO
        - furniture - furniture.getId() != null
        - wood  - wood.getId() != null
        - quantity != null, > 0
        - price != null, > BigDecimal.ZERO
 */

import org.springframework.stereotype.Component;
import project.furnitureworkshop.demo.controller.dto.OrderDTO;
import project.furnitureworkshop.demo.controller.dto.OrderItemDTO;
import project.furnitureworkshop.demo.exception.ValidationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderValidator {

    public void validateOrder(OrderDTO orderDTO) {
        List<String> violations = new ArrayList<>();
        validateClient(orderDTO, violations);
        validateOrderDate(orderDTO, violations);
        validateOderItem(orderDTO.getOrdersItems(), violations);


        if (!violations.isEmpty()) {
            throw new ValidationException("Provided order is invalid!", violations);
        }

    }

    private void validateOrderDate(OrderDTO orderDTO, List<String> violations) {
        if (orderDTO.getOrderDate() == null) {
            violations.add("Date of order is null");
        }
    }

    private void validateClient(OrderDTO orderDTO, List<String> violations) {
        if (orderDTO.getClient().getId() == null) {
            violations.add("The client could not be found");
        }
    }


    private void validateOderItem(List<OrderItemDTO> orderItemDTO, List<String> violations) {
        if (orderItemDTO == null || orderItemDTO.isEmpty()) {
            violations.add("Orders item is null or empty");
            validateFurnitureId((OrderItemDTO) orderItemDTO, violations);
            validateWoodId((OrderItemDTO) orderItemDTO, violations);
            validateQuantity((OrderItemDTO) orderItemDTO, violations);
            validatePrice((OrderItemDTO) orderItemDTO, violations);
            //не уверен что это правильная валидация на orderitem - не пустой массив
        }
    }

    private void validatePrice(OrderItemDTO orderItemDTO, List<String> violations) {
        if (orderItemDTO.getPrice() == null) {
            violations.add("This price is null");
        } else if (orderItemDTO.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            violations.add("This price must be greater than zero");
        }
    }

    private void validateQuantity(OrderItemDTO orderItemDTO, List<String> violations) {
        if (orderItemDTO.getQuantity() == null) {
            violations.add("Quantity is null");
        } else if (orderItemDTO.getQuantity() <= 0) {
            violations.add("The quantity must not be zero");
        }
    }

    private void validateWoodId(OrderItemDTO orderItemDTO, List<String> violations) {
        if (orderItemDTO.getWood().getId() == null) {
            violations.add("The wood could not be found");
        }
    }

    private void validateFurnitureId(OrderItemDTO orderItemDTO, List<String> violations) {
        if (orderItemDTO.getFurniture().getId() == null) {
            violations.add("The furniture could not be found");
        }

    }
}
