package com.adviters.virtualwallet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Clase que representa la tabla de monedas

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "currencies")
public class Currency extends BaseEntity {

    @Column(name = "currency_name")
    private String currencyName;
}