package project.furnitureworkshop.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import project.furnitureworkshop.demo.controller.dto.*;
import project.furnitureworkshop.demo.exception.ValidationException;
import project.furnitureworkshop.demo.service.CurrencyService;
import project.furnitureworkshop.demo.service.FurnitureService;
import project.furnitureworkshop.demo.service.OrderService;
import project.furnitureworkshop.demo.service.WoodSpeccyService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Order management API", description = "API for CRUD operations with order")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final CurrencyService currencyService;

    private final FurnitureService furnitureService;
    private final WoodSpeccyService woodService;

    public OrderController(OrderService orderService, CurrencyService currencyService, FurnitureService furnitureService, WoodSpeccyService woodService) {
        this.orderService = orderService;
        this.currencyService = currencyService;
        this.furnitureService = furnitureService;

        this.woodService = woodService;
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

    @Operation(description = "This procedure is designed to calculate the total price (price for furniture, price per volume of wood, number of products) and convert it from USD to EUR.")
    @GetMapping("/price")
    public OrderInfoDTO getTotalPriceInEur(@RequestParam Integer furnitureId,
                                     @RequestParam Integer woodId,
                                     @RequestParam Integer quantity) {

        if (furnitureId == null || woodId == null || quantity == null) {
            List<String> violations = new ArrayList<>();
            violations.add("Parameter  is null!");
            throw new ValidationException("Invalid price parameters!", violations);
        }
        BigDecimal totalPriceInPln = orderService.calculateTotalPrice(furnitureId, woodId, quantity);
        CurrencyDTO eurCurrency = currencyService.findForeignExchange("PLN");
        BigDecimal exchangeRate = eurCurrency.getRate();
        BigDecimal totalPriceInEur = totalPriceInPln.divide(exchangeRate, 2, RoundingMode.HALF_UP);

        FurnitureDTO furnitureDTO = furnitureService.getById(furnitureId);
        WoodSpeccyDTO woodSpeccyDTO = woodService.getById(woodId);

        OrderInfoDTO orderInfo = new OrderInfoDTO();
        orderInfo.setTotalPriceInUsd(totalPriceInPln);
        orderInfo.setTotalPriceInEur(totalPriceInEur);
        orderInfo.setFurnitureId(furnitureDTO);
        orderInfo.setWoodId(woodSpeccyDTO);
        orderInfo.setQuantity(quantity);
        return orderInfo;
    }
}






//        BigDecimal totalPriceInUsd = orderService.calculateTotalPrice(furnitureId, woodId, quantity);
//        CurrencyDTO eurCurrency = currencyService.findForeignExchange("PLN");
//        BigDecimal exchangeRate = eurCurrency.getRate();
//        BigDecimal totalPriceInEur = totalPriceInUsd.divide(exchangeRate, 2, RoundingMode.HALF_UP);
//
//        return String.format("%.2f PLN -> %.2f USD", totalPriceInUsd, totalPriceInEur);
