package project.furnitureworkshop.demo.service;

import project.furnitureworkshop.demo.controller.dto.CurrencyDTO;

public interface CurrencyService {

    CurrencyDTO findForeignExchange(String currencyCode);
}
