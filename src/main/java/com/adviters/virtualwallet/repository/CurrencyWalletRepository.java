package com.adviters.virtualwallet.repository;

import com.adviters.virtualwallet.model.CurrencyWallet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//Repositorio de clase intermedia entre billetera y moneda

public interface CurrencyWalletRepository extends CrudRepository<CurrencyWallet, Long> {

    Optional<CurrencyWallet> findById(Long id);

    List<CurrencyWallet> findAllByWallet_Id(Long id);
}
