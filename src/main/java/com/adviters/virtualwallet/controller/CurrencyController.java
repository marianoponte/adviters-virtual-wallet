package com.adviters.virtualwallet.controller;

import com.adviters.virtualwallet.exception.ResourceNotFoundException;
import com.adviters.virtualwallet.model.Currency;
import com.adviters.virtualwallet.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Clase de controlador para las monedas

@RestController
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/currency/{id}")
    public ResponseEntity<Currency> getCurrency(@PathVariable Long id) {
        Currency currency = currencyService.getCurrency(id);
        if (currency != null) {
            return new ResponseEntity<Currency>(currency, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Currency not found!");
        }
    }

    @GetMapping("/currencies")
    public ResponseEntity<List<Currency>> getCurrencies() {
        List<Currency> currencies = currencyService.getCurrencies();
        return new ResponseEntity<List<Currency>>(currencies,HttpStatus.OK);
    }
}
