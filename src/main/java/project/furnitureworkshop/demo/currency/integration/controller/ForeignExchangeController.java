package project.furnitureworkshop.demo.currency.integration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.furnitureworkshop.demo.currency.integration.dto.ForeignExchangeDTO;
import project.furnitureworkshop.demo.currency.integration.service.ForeignExchangeService;

@RestController
@RequestMapping("/currencies")
public class ForeignExchangeController {

    private final ForeignExchangeService foreignExchangeService;

    public ForeignExchangeController(ForeignExchangeService foreignExchangeService) {
        this.foreignExchangeService = foreignExchangeService;
    }

    @GetMapping
    public ForeignExchangeDTO getForeignExchange(@RequestParam String currencyCode) {
        return foreignExchangeService.findForeignExchange(currencyCode);
    }
}
