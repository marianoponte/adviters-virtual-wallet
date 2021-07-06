package com.adviters.virtualwallet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Clase POJO de transaccion

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionPayload {

    private Long idCurrencyWallet;

    private String transactionType;

    private LocalDateTime creationDate;

    private BigDecimal amount;
}