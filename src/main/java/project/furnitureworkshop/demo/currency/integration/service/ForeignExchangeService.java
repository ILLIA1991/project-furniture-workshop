package project.furnitureworkshop.demo.currency.integration.service;

import project.furnitureworkshop.demo.currency.integration.dto.ForeignExchangeDTO;

public interface ForeignExchangeService {

    ForeignExchangeDTO findForeignExchange(String currencyCode);
}
