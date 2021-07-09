package com.adviters.virtualwallet.service;

import com.adviters.virtualwallet.model.Currency;
import com.adviters.virtualwallet.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Clase de servicio para las monedas

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    //Obtener una moneda por id
    public Currency getCurrency(Long id) {
        Optional<Currency> currency = currencyRepository.findById(id);
        if (currency.isPresent()) {
            return currency.get();
        } else
            return null;
    }

    //Obtener todas las monedas
    public List<Currency> getCurrencies() {
        return (List<Currency>) currencyRepository.findAll();
    }
}
