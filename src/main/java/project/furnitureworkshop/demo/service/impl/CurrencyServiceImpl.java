package project.furnitureworkshop.demo.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.furnitureworkshop.demo.controller.dto.CurrencyDTO;
import project.furnitureworkshop.demo.repository.model.Currency;
import project.furnitureworkshop.demo.service.CurrencyService;
import project.furnitureworkshop.demo.exception.FurnitureWorkshopNotFoundException;

import java.util.Arrays;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private static final String FOREIGN_EXCHANGE = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    @Override
    public CurrencyDTO findForeignExchange(String currencyCode) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Currency[]> response = restTemplate.getForEntity(
                FOREIGN_EXCHANGE,
                Currency[].class
        );

        Currency[] resultArray = response.getBody();

        if (resultArray != null && resultArray.length > 0) {

            Optional<Currency> exchangeOptional = Arrays.stream(resultArray)
                    .filter(exchange -> currencyCode.equalsIgnoreCase(exchange.getCc()))
                    .findAny();

            return exchangeOptional.map(exchange -> new CurrencyDTO(
                    exchange.getR030(),
                    exchange.getTxt(),
                    exchange.getRate(),
                    exchange.getCc(),
                    exchange.getExchangeDate()
            )).orElseThrow(() -> new FurnitureWorkshopNotFoundException(String.format("This currency was not detected: %s", currencyCode)));
        } else {
            throw new FurnitureWorkshopNotFoundException(String.format("This currency was not detected: %s", currencyCode));
        }
    }
}
