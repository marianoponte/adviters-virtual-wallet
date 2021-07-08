package com.adviters.virtualwallet.repository;

import com.adviters.virtualwallet.model.Currency;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//Repositorio de clase moneda

public interface CurrencyRepository extends CrudRepository<Currency,Long> {

    Optional<Currency> findById(Long id);

    Currency findByCurrencyName(String currencyName);
}
