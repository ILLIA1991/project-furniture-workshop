package project.furnitureworkshop.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import project.furnitureworkshop.demo.controller.dto.OrderDTO;
import project.furnitureworkshop.demo.exception.ValidationException;
import project.furnitureworkshop.demo.service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Order management API", description = "API for CRUD operations with order")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(description = "Search for a specific order by ID. Access only to the admin.")
    @GetMapping("/{id}")
    public OrderDTO getById(@PathVariable Integer id) {
        logger.trace(String.format("requested orders# $s", id));
        return orderService.getById(id);
    }

    @Operation(description = "Search for all orders that are currently in the database. Access only to the admin.")
    @GetMapping
    public List<OrderDTO> getAll() {
        return orderService.getAll();
    }

    @Operation(description = "Deletion of our order after fulfillment. Access only to the admin. ")
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
    }

    @Operation(description = "Creating an order after selecting furniture, type of wood and quantity of this product. Access for consideration only from the admin.")
    @PostMapping
    public Integer createOrder(@RequestBody OrderDTO orderToCreate) {
        return orderService.createOrder(orderToCreate);
    }

    @Operation(description = "This procedure is designed to calculate the total price (price for furniture, price per volume of wood, number of products).")
    @GetMapping("/price")
    public BigDecimal getTotalPrice(@RequestParam Integer furnitureId,
                                    @RequestParam Integer woodId,
                                    @RequestParam Integer quantity) {

        if (furnitureId == null || woodId == null || quantity == null) {
            List<String> violations = new ArrayList<>();
            violations.add("Parameter is null!");
            throw new ValidationException("Parameter is null!", violations);
        }

        return orderService.calculateTotalPrice(furnitureId, woodId, quantity);
    }
}
