package com.adviters.virtualwallet.repository;

import com.adviters.virtualwallet.model.Quotation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//Repositorio de clase cotizacion

public interface QuotationRepository extends CrudRepository<Quotation, Long> {

    Optional<Quotation> findById(Long id);

    //Filtro las cotizaciones por las fechas recibidas como parametros
    @Query(value = "from Quotation t where t.quotationDate BETWEEN :from AND :to")
    List<Quotation> findAllByFromAndToDates(@Param("from")LocalDateTime from, @Param("to")LocalDateTime to);

    @Query(value = "from Quotation t where t.quotationDate BETWEEN :date AND :date")
    Optional<Quotation> findByQuotationDateParam(@Param("date") LocalDateTime date);

}
