package com.adviters.virtualwallet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// Clase que representa la tabla de transacciones

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_currencywallet")
    private CurrencyWallet currencyWallet;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "amount")
    private BigDecimal amount;
}
