package project.furnitureworkshop.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.furnitureworkshop.demo.controller.dto.CurrencyDTO;
import project.furnitureworkshop.demo.service.CurrencyService;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public CurrencyDTO getForeignExchange(@RequestParam String currencyCode) {
        return currencyService.findForeignExchange(currencyCode);
    }
}
