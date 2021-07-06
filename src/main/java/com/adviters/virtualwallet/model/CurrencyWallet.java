package com.adviters.virtualwallet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// Clase intermedia entre la billetera y las monedas

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "currency_wallet")
public class CurrencyWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_currency")
    private Currency currency;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_wallet")
    private Wallet wallet;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}
