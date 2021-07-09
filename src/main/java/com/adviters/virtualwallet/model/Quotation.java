package com.adviters.virtualwallet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

// Clase que representa la tabla de cotizaciones

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quotes")
public class Quotation extends BaseEntity {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_currency")
    private Currency currency;

    @Column(name = "quotation_date")
    private LocalDateTime quotationDate;

    @Column(name = "price")
    private Double price;
}
