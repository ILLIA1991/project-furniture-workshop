package project.furnitureworkshop.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.furnitureworkshop.demo.controller.dto.OrderItemDTO;
import project.furnitureworkshop.demo.service.OrderItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
public class OrderItemController {

    private final OrderItemService itemService;

    public OrderItemController(OrderItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<OrderItemDTO> getAll() {
        return itemService.getAllOrdersItems();
    }


}
