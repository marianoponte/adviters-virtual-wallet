package com.adviters.virtualwallet.repository;

import com.adviters.virtualwallet.model.Wallet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//Repositorio de clase billetera

public interface WalletRepository extends CrudRepository<Wallet, Long> {

    Optional<Wallet> findById(Long id);
}
