package com.adviters.virtualwallet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

// Clase que representa la tabla de billeteras

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wallets")
public class Wallet extends BaseEntity {

    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}