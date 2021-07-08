package com.adviters.virtualwallet.repository;

import com.adviters.virtualwallet.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//Repositorio de clase transaccion

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Optional<Transaction> findById(Long id);
}
