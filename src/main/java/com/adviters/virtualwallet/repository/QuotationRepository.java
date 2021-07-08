package com.adviters.virtualwallet.repository;

import com.adviters.virtualwallet.model.Quotation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//Repositorio de clase cotizacion

public interface QuotationRepository extends CrudRepository<Quotation, Long> {

    Optional<Quotation> findById(Long id);

}
