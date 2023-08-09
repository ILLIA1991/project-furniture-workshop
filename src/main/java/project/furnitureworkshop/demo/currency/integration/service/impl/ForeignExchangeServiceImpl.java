package project.furnitureworkshop.demo.currency.integration.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.furnitureworkshop.demo.currency.integration.dto.ForeignExchangeDTO;
import project.furnitureworkshop.demo.currency.integration.model.ForeignExchange;
import project.furnitureworkshop.demo.currency.integration.service.ForeignExchangeService;
import project.furnitureworkshop.demo.exception.FurnitureWorkshopNotFoundException;

import java.util.Arrays;
import java.util.Optional;


@Service
public class ForeignExchangeServiceImpl implements ForeignExchangeService {

    private static final String FOREIGN_EXCHANGE = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    @Override
    public ForeignExchangeDTO findForeignExchange(String currencyCode) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ForeignExchange[]> response = restTemplate.getForEntity(
                FOREIGN_EXCHANGE,
                ForeignExchange[].class
        );

        ForeignExchange[] resultArray = response.getBody();

        if (resultArray != null && resultArray.length > 0) {

            Optional<ForeignExchange> exchangeOptional = Arrays.stream(resultArray)
                    .filter(exchange -> currencyCode.equalsIgnoreCase(exchange.getCc()))
                    .findAny();

            return exchangeOptional.map(exchange -> new ForeignExchangeDTO(
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
